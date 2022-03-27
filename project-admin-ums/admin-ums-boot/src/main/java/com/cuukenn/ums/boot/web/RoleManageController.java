package com.cuukenn.ums.boot.web;

import com.cuukenn.core.api.ApiPage;
import com.cuukenn.core.api.ApiResult;
import com.cuukenn.core.api.PageQuery;
import com.cuukenn.core.exception.BizException;
import com.cuukenn.core.util.Assert;
import com.cuukenn.ums.pojo.command.RoleCommand;
import com.cuukenn.ums.pojo.dto.RoleDTO;
import com.cuukenn.ums.boot.assembler.RoleAssembler;
import com.cuukenn.ums.boot.entity.Role;
import com.cuukenn.ums.boot.service.RoleManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 角色管理API
 *
 * @author changgg
 */
@RestController
@RequestMapping(value = "/api/user/role")
@RequiredArgsConstructor
@Slf4j
public class RoleManageController {
    private final RoleManageService roleManageService;
    private final RoleAssembler roleAssembler;

    /**
     * 分页查询用户列表
     *
     * @param query 查询参数
     * @return list
     */
    @GetMapping(value = "/list")
    public ApiResult<ApiPage<RoleDTO>> list(PageQuery query) {
        Page<Role> page = roleManageService.list(query);
        return ApiResult.success(page.getNumber(), page.getSize(), page.getTotalElements(),
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(page.iterator(), Spliterator.ORDERED), false)
                        .map(roleAssembler::toDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 增加角色
     *
     * @return result
     */
    @PostMapping(value = "/add")
    public ApiResult<Void> addRole(RoleCommand command) {
        Assert.isNull(command.getId(), () -> new BizException("增加实体时ID无需携带"));
        roleManageService.addRole(command);
        return ApiResult.success();
    }

    /**
     * 更新角色
     *
     * @return result
     */
    @PostMapping(value = "/update")
    public ApiResult<Void> updateRole(RoleCommand command) {
        Assert.notNull(command.getId(), () -> new BizException("更新实体时ID需要携带"));
        roleManageService.updateRole(command);
        return ApiResult.success();
    }

    /**
     * 删除角色
     *
     * @return result
     */
    @PostMapping(value = "/delete")
    public ApiResult<Void> deleteRole(Long id) {
        roleManageService.deleteRole(id);
        return ApiResult.success();
    }
}
