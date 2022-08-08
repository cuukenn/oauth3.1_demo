package com.cuukenn.ums.boot.service;

import com.cuukenn.core.api.PageQuery;
import com.cuukenn.ums.pojo.command.RoleCommand;
import com.cuukenn.ums.boot.entity.Role;
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
