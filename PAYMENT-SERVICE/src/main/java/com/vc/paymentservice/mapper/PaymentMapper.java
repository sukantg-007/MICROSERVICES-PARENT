package com.vc.paymentservice.mapper;

import com.vc.common.dto.PaymentDTO;
import com.vc.paymentservice.dto.PaymentRequest;
import com.vc.paymentservice.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDTO toPaymentDTO(Payment payment);

    Payment toPayment(PaymentRequest paymentRequest);
}
