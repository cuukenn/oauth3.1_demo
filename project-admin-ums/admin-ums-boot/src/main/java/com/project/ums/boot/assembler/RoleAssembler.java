package com.project.ums.boot.assembler;

import com.project.core.assembler.BaseAssembler;
import com.project.ums.pojo.dto.RoleDTO;
import com.project.ums.boot.entity.Role;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface RoleAssembler extends BaseAssembler<RoleDTO, Role> {
}
