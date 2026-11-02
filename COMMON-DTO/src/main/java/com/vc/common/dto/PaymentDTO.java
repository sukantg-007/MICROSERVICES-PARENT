package com.vc.common.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentDTO (
        UUID txnId,
        BigDecimal amount,
        String currency,
        LocalDateTime txnDate,
        PaymentStatus status
){
}
