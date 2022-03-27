package com.cuukenn.ums.boot.assembler;

import com.cuukenn.core.assembler.BaseAssembler;
import com.cuukenn.ums.pojo.dto.UserDTO;
import com.cuukenn.ums.boot.entity.User;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface UserAssembler extends BaseAssembler<UserDTO, User> {
}
