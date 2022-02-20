package com.project.ums.server.service;

import com.project.core.api.PageQuery;
import com.project.ums.server.entity.Role;
import com.project.ums.server.poj.command.RoleCommand;
import org.springframework.data.domain.Page;

/**
 * 角色管理服务
 *
 * @author changgg
 */
public interface RoleManageService {
    /**
     * 分页查询角色列表
     *
     * @param query 查询
     * @return 分页数据
     */
    Page<Role> list(PageQuery query);

    /**
     * 增加角色
     *
     * @param command 载体
     */
    void addRole(RoleCommand command);

    /**
     * 更新角色
     *
     * @param command 载体
     */
    void updateRole(RoleCommand command);

    /**
     * 删除角色
     *
     * @param id 角色ID
     */
    void deleteRole(Long id);
}
