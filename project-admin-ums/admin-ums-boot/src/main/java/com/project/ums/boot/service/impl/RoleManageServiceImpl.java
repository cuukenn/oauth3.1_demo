package com.project.ums.boot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.project.core.api.PageQuery;
import com.project.core.exception.BizException;
import com.project.ums.pojo.command.RoleCommand;
import com.project.ums.boot.assembler.RoleAssembler;
import com.project.ums.boot.entity.Role;
import com.project.ums.boot.entity.Role_;
import com.project.ums.boot.repository.RoleRepository;
import com.project.ums.boot.service.RoleManageService;
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
 * 角色管理服务
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleManageServiceImpl implements RoleManageService {
    private final RoleRepository repository;
    private final RoleAssembler assembler;

    @Override
    public Page<Role> list(PageQuery pageQuery) {
        return repository.findAll(
                (Specification<Role>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (StrUtil.isNotBlank(pageQuery.getFilter())) {
                        predicateList.add(criteriaBuilder.like(root.get(Role_.ROLE).as(Role_.role.getJavaType()), "%" + pageQuery.getFilter() + "%"));
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
    public void addRole(RoleCommand command) {
        Role newEntity = assembler.toEntity(command);
        newEntity.setId(null);
        newEntity.setRole(command.getRole());
        repository.save(newEntity);
    }

    @Override
    public void updateRole(RoleCommand command) {
        Role entity = repository.findById(command.getId()).orElseThrow(() -> new BizException(String.format("角色[id:%d]不存在,无法更新", command.getId())));
        assembler.updateEntity(command, entity);
        repository.save(entity);
    }

    @Override
    public void deleteRole(Long id) {
        repository.findById(id).orElseThrow(() -> new BizException(String.format("角色[id:%d]不存在,无需删除", id)));
    }
}
