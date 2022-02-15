package com.project.ums.server.poj.dto;

import com.project.core.base.BaseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;

/**
 * 角色实体传输对象
 *
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
@Data
public class RoleDTO implements BaseDTO {
    private static final long serialVersionUID = -654684441163763629L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名
     */
    @Max(20)
    private String role;
}
