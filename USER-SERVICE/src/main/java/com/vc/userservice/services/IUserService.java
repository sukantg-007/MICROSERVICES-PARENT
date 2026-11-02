package com.vc.userservice.services;

import com.vc.common.dto.UserDTO;
import com.vc.userservice.dto.UserRequest;
import com.vc.userservice.entity.User;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserRequest request);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();
}
