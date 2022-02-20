package com.project.ums.server.web;

import com.project.core.api.ApiPage;
import com.project.core.api.ApiResult;
import com.project.core.api.PageQuery;
import com.project.core.exception.BizException;
import com.project.core.util.Assert;
import com.project.ums.server.assembler.UserAssembler;
import com.project.ums.server.entity.User;
import com.project.ums.server.poj.command.ChangePasswordCommand;
import com.project.ums.server.poj.command.UserCommand;
import com.project.ums.server.poj.dto.UserDTO;
import com.project.ums.server.service.UserManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 用户管理API
 *
 * @author changgg
 */
@RestController
@RequestMapping("/api/user/manage")
@RequiredArgsConstructor
public class UserManageController {
    private final UserManageService userManageService;
    private final UserAssembler userAssembler;

    /**
     * 分页查询用户列表
     *
     * @param query 查询参数
     * @return list
     */
    @GetMapping(value = "/list")
    public ApiResult<ApiPage<UserDTO>> list(PageQuery query) {
        Page<User> page = userManageService.list(query);
        return ApiResult.success(page.getNumber(), page.getSize(), page.getTotalElements(),
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(page.iterator(), Spliterator.ORDERED), false)
                        .map(userAssembler::toDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 增加用户
     *
     * @return result
     */
    @PostMapping(value = "/add")
    public ApiResult<Void> addUser(UserCommand command) {
        Assert.isNull(command.getId(), () -> new BizException("增加实体时ID无需携带"));
        userManageService.addUser(command);
        return ApiResult.success();
    }

    /**
     * 更新用户
     *
     * @return result
     */
    @PostMapping(value = "/update")
    public ApiResult<Void> updateUser(UserCommand command) {
        Assert.notNull(command.getId(), () -> new BizException("更新实体时ID需要携带"));
        userManageService.updateUser(command);
        return ApiResult.success();
    }

    /**
     * 删除用户
     *
     * @return result
     */
    @PostMapping(value = "/delete")
    public ApiResult<Void> deleteUser(Long id) {
        userManageService.deleteUser(id);
        return ApiResult.success();
    }

    /**
     * 重置密码
     *
     * @return result
     */
    @PostMapping(value = "/reset-password")
    public ApiResult<Void> resetPassword(Long id) {
        userManageService.resetPassword(id);
        return ApiResult.success();
    }

    /**
     * 更改密码
     *
     * @return result
     */
    @PostMapping(value = "/change-password")
    public ApiResult<Void> changePassword(ChangePasswordCommand command) {
        userManageService.changePassword(command);
        return ApiResult.success();
    }

    /**
     * 更改密码
     *
     * @return result
     */
    @PostMapping(value = "/change-password/{username}")
    public ApiResult<Void> changePasswordWithUsername(@PathVariable String username, ChangePasswordCommand command) {
        userManageService.changePassword(username, command);
        return ApiResult.success();
    }
}
