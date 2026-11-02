package com.vc.userservice.dto;

import com.vc.common.dto.UserStatus;
import lombok.Builder;

@Builder
public record UserRequest(
        String name,
        String email,
        String mobile,
        UserStatus status) {
}
