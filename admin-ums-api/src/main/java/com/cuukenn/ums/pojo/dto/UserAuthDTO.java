package com.cuukenn.ums.pojo.dto;

import com.cuukenn.core.base.BaseDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * @author changgg
 */
@Data
public class UserAuthDTO implements BaseDTO {
    private static final long serialVersionUID = -2247253880765509396L;
    @Length(max = 12)
    private String username;
    @Length(max = 128)
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Set<RoleDTO> authorities;
}
