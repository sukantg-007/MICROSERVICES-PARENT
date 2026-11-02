package com.vc.userservice.mapper;

import com.vc.common.dto.UserDTO;
import com.vc.userservice.dto.UserRequest;
import com.vc.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUseEntity(UserRequest userRequest);

    UserDTO toUserResponse(User user);
}
