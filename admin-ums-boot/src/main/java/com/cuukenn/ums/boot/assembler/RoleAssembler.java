package com.cuukenn.ums.boot.assembler;

import com.cuukenn.core.assembler.BaseAssembler;
import com.cuukenn.ums.pojo.dto.RoleDTO;
import com.cuukenn.ums.boot.entity.Role;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface RoleAssembler extends BaseAssembler<RoleDTO, Role> {
}
