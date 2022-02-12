package com.project.ums.server.assembler;

import com.project.core.assembler.BaseAssembler;
import com.project.ums.server.entity.User;
import com.project.ums.server.poj.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface UserAssembler extends BaseAssembler<UserDTO, User> {
}
