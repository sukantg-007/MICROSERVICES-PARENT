package com.vc.paymentservice.dto;

import java.util.UUID;

public record PaymentGatewayResult(
        UUID transactionId,
        boolean success,
        String failureReason
) {
    public static PaymentGatewayResult successful(UUID transactionId) {
        return new PaymentGatewayResult(transactionId, true, null);
    }

    public static PaymentGatewayResult failed(String reason) {
        return new PaymentGatewayResult(null, false, reason);
    }
}
