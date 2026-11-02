package com.vc.common.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        int status,
        String message,
        String error,
        String path,
        LocalDateTime timestamp) {
}
