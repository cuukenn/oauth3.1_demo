package com.cuukenn.ums.pojo.dto;

import com.cuukenn.core.base.BaseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 100)
    private String role;
}
