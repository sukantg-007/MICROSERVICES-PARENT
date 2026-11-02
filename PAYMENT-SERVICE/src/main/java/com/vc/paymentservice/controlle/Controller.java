package com.vc.paymentservice.controlle;

import com.vc.common.dto.PaymentDTO;
import com.vc.paymentservice.dto.PaymentRequest;
import com.vc.paymentservice.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
public class Controller {
    private final IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(
            @PathVariable Long id,
            @RequestBody PaymentRequest request) {

        return ResponseEntity.ok(paymentService.updatePayment(id, request));

    }
}
