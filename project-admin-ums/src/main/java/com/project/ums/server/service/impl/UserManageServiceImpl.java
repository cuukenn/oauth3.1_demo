package com.project.ums.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.project.core.api.PageQuery;
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
    }

    @Override
    public void updateUser(UserCommand command) {
    }

    @Override
    public void deleteUser(Long id) {
    }

    @Override
    public void resetPassword(Long id) {
    }

    @Override
    public void changePassword(ChangePasswordCommand command) {
    }

    @Override
    public void changePassword(String username, ChangePasswordCommand command) {
    }

    /**
     * 修改密码
     *
     * @param entity  实体
     * @param command 命令
     */
    private void changePassword(User entity, ChangePasswordCommand command) {
    }
}
