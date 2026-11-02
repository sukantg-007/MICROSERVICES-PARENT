package com.vc.userservice.services;

import com.vc.common.dto.UserDTO;
import com.vc.common.dto.UserStatus;
import com.vc.userservice.dto.UserRequest;
import com.vc.userservice.entity.User;
import com.vc.userservice.exception.DuplicateEmailException;
import com.vc.userservice.exception.UserNotFoundException;
import com.vc.userservice.mapper.UserMapper;
import com.vc.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }
        User user = userMapper.toUseEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        User savedUser =userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
}
