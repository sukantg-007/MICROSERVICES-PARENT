package com.vc.common.dto;


public record UserDTO(
        Long id,
        String name,
        String email,
        String mobile,
        UserStatus status
) {
}
