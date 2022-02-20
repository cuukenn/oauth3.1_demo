package com.project.ums.boot.service;

import com.project.core.api.PageQuery;
import com.project.ums.pojo.command.ChangePasswordCommand;
import com.project.ums.pojo.command.UserCommand;
import com.project.ums.boot.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * 用户管理服务
 *
 * @author changgg
 */
public interface UserManageService {
    /**
     * 分页查询用户列表
     *
     * @param query 查询
     * @return 分页数据
     */
    Page<User> list(PageQuery query);

    /**
     * 增加用户
     *
     * @param command 用户载体
     */
    void addUser(UserCommand command);

    /**
     * 更新用户
     *
     * @param command 用户载体
     */
    void updateUser(UserCommand command);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Long id);

    /**
     * 重置密码
     *
     * @param id 用户ID
     */
    void resetPassword(Long id);

    /**
     * 修改密码
     *
     * @param command 命令
     */
    void changePassword(ChangePasswordCommand command);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param command  命令
     */
    void changePassword(String username, ChangePasswordCommand command);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @return 数据
     */
    Optional<User> findByUsername(String username);
}
