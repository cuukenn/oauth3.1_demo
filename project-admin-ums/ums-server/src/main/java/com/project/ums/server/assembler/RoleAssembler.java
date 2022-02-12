package com.project.ums.server.assembler;

import com.project.core.assembler.BaseAssembler;
import com.project.ums.server.entity.Role;
import com.project.ums.server.poj.dto.RoleDTO;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface RoleAssembler extends BaseAssembler<RoleDTO, Role> {
}
