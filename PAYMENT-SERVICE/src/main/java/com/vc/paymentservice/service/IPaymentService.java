package com.vc.paymentservice.service;

import com.vc.common.dto.PaymentDTO;
import com.vc.paymentservice.dto.PaymentRequest;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {

    PaymentDTO createPayment(PaymentRequest paymentRequest);

    PaymentDTO getPaymentById(UUID paymentId);

    PaymentDTO updatePayment(Long paymentId, PaymentRequest paymentRequest);

    List<PaymentDTO> getAllPayments();

}
