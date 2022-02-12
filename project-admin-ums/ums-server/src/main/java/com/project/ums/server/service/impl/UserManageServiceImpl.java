package com.project.ums.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.project.core.api.ApiCodes;
import com.project.core.api.PageQuery;
import com.project.core.constant.Constant;
import com.project.core.exception.ApiException;
import com.project.core.util.Assert;
import com.project.security.util.SecurityUtils;
import com.project.ums.server.assembler.UserAssembler;
import com.project.ums.server.entity.User;
import com.project.ums.server.entity.User_;
import com.project.ums.server.poj.command.ChangePasswordCommand;
import com.project.ums.server.poj.command.UserCommand;
import com.project.ums.server.repository.UserRepository;
import com.project.ums.server.service.UserManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户管理服务
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserAssembler assembler;

    @Override
    public Page<User> list(PageQuery pageQuery) {
        return repository.findAll(
                (Specification<User>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (StrUtil.isNotBlank(pageQuery.getFilter())) {
                        predicateList.add(criteriaBuilder.like(root.get(User_.USERNAME).as(User_.username.getJavaType()), "%" + pageQuery.getFilter() + "%"));
                    }
                    return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
                },
                PageRequest.of(pageQuery.getPageNumber(),
                        pageQuery.getPageSize(),
                        Sort.by(pageQuery.transformColumnOrder().stream()
                                .map(item -> item.isAsc() ? Sort.Order.asc(item.getColumn()) : Sort.Order.desc(item.getColumn()))
                                .collect(Collectors.toList())))
        );
    }

    @Override
    public void addUser(UserCommand command) {
        User newEntity = assembler.toEntity(command);
        newEntity.setId(null);
        newEntity.setPassword(passwordEncoder.encode(Constant.INIT_PASSWORD));
        newEntity.setEnabled(true);
        newEntity.setAccountNonExpired(true);
        newEntity.setAccountNonLocked(true);
        //新用户强制更新密码
        newEntity.setCredentialsNonExpired(false);
        repository.save(newEntity);
    }

    @Override
    public void updateUser(UserCommand command) {
        User entity = repository.findById(command.getId()).orElseThrow(() -> new ApiException(ApiCodes.AUTH_FAILED, String.format("用户[id:%d]不存在,无法更新", command.getId())));
        assembler.updateEntity(command, entity);
        repository.save(entity);
    }

    @Override
    public void deleteUser(Long id) {
        repository.findById(id).orElseThrow(() -> new ApiException(ApiCodes.AUTH_FAILED, String.format("用户[id:%d]不存在,无需删除", id)));
    }

    @Override
    public void resetPassword(Long id) {
        User entity = repository.findById(id).orElseThrow(() -> new ApiException(ApiCodes.AUTH_FAILED, String.format("用户[id:%d]不存在,无法重置密码", id)));
        entity.setPassword(passwordEncoder.encode(Constant.INIT_PASSWORD));
        //新用户强制更新密码
        entity.setCredentialsNonExpired(false);
        repository.save(entity);
    }

    @Override
    public void changePassword(ChangePasswordCommand command) {
        command.validAndAssert();
        Long currentUserId = SecurityUtils.getCurrentUserId();
        User entity = repository.findById(currentUserId).orElseThrow(() -> new ApiException(ApiCodes.AUTH_FAILED, String.format("用户[id:%d]不存在,无法修改密码", currentUserId)));
        changePassword(entity, command);
    }

    @Override
    public void changePassword(String username, ChangePasswordCommand command) {
        command.validAndAssert();
        User entity = repository.findByUsername(username).orElseThrow(() -> new ApiException(ApiCodes.AUTH_FAILED, String.format("用户[username:%s]不存在,无法修改密码", username)));
        changePassword(entity, command);
    }

    /**
     * 修改密码
     *
     * @param entity  实体
     * @param command 命令
     */
    private void changePassword(User entity, ChangePasswordCommand command) {
        Assert.isTrue(passwordEncoder.matches(command.getOldPassword(), entity.getPassword()), () -> new ApiException(ApiCodes.AUTH_FAILED, "旧密码错误"));
        entity.setPassword(passwordEncoder.encode(Constant.INIT_PASSWORD));
        //修改密码后需要改成密码未过期
        entity.setCredentialsNonExpired(true);
        repository.save(entity);
    }
}
