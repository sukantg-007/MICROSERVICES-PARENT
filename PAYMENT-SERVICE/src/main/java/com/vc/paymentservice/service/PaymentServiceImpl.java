package com.vc.paymentservice.service;

import com.vc.common.dto.PaymentDTO;
import com.vc.common.dto.PaymentStatus;
import com.vc.paymentservice.dto.PaymentGatewayResult;
import com.vc.paymentservice.dto.PaymentRequest;
import com.vc.paymentservice.entity.Payment;
import com.vc.paymentservice.exception.PaymentNotFoundException;
import com.vc.paymentservice.mapper.PaymentMapper;
import com.vc.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService{
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    @Override
    @Transactional(noRollbackFor = PaymentNotFoundException.class)
    public PaymentDTO createPayment(PaymentRequest paymentRequest) {
        Payment payment = mapper.toPayment(paymentRequest);
        Payment saved = null;
        PaymentGatewayResult paymentDetails = getPaymentDetails(paymentRequest);
        if(paymentDetails.success()){
            payment.setId(paymentDetails.transactionId());
            payment.setStatus(PaymentStatus.COMPLETED);
            saved = paymentRepository.save(payment);
        }else{
            payment.setId(paymentDetails.transactionId());
            payment.setStatus(PaymentStatus.FAILED);
            saved = paymentRepository.save(payment);
            throw new PaymentNotFoundException("Payment failed for request: " + paymentRequest);
        }
        return mapper.toPaymentDTO(saved);
    }

    @Override
    public PaymentDTO getPaymentById(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment == null) {
            throw new PaymentNotFoundException("Payment not found for id: " + paymentId);
        }
        return mapper.toPaymentDTO(payment);
    }

    @Override
    public PaymentDTO updatePayment(Long seatId, PaymentRequest paymentRequest) {
        Payment existPayment = paymentRepository.findBySeatId(seatId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for seatId: " + seatId));

        PaymentGatewayResult paymentDetails = getPaymentDetails(paymentRequest);
        existPayment.setTxnDate(LocalDateTime.now());
        existPayment.setId(paymentDetails.transactionId());
        existPayment.setStatus(paymentDetails.success() ? PaymentStatus.COMPLETED : PaymentStatus.FAILED);
        Payment saved =paymentRepository.save(existPayment);
        return mapper.toPaymentDTO(saved);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(mapper::toPaymentDTO)
                .toList();
    }

    private PaymentGatewayResult getPaymentDetails(PaymentRequest paymentRequest) {
        UUID transactionId = UUID.randomUUID();
        // In real gateway integration: evaluate HTTP status or response codes
        return PaymentGatewayResult.successful(transactionId);
    }
}
