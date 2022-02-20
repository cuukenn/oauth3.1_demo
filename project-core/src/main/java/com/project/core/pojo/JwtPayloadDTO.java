package com.project.core.pojo;

import com.project.core.base.BaseDTO;
import lombok.Data;

/**
 * @author changgg
 */
@Data
public class JwtPayloadDTO implements BaseDTO {
    private static final long serialVersionUID = 2010743303124208011L;
    private Long userId;
    private String username;
}
