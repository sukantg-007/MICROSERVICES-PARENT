package com.vc.orderservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderRequest(
        Long showId,
        String seatNumber,
        Long userId,
        BigDecimal amount) {
}
