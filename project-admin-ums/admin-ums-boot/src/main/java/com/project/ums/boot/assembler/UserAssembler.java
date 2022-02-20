package com.project.ums.boot.assembler;

import com.project.core.assembler.BaseAssembler;
import com.project.ums.pojo.dto.UserDTO;
import com.project.ums.boot.entity.User;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface UserAssembler extends BaseAssembler<UserDTO, User> {
}
