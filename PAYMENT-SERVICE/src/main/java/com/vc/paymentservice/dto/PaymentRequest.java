package com.vc.paymentservice.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        String currency,
        String description
) {
}
