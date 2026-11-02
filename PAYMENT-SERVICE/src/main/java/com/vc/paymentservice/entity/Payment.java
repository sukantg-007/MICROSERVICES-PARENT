package com.vc.paymentservice.entity;

import com.vc.common.dto.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount",nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "txn_date", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "failure_reason", nullable = true)
    private String failureReason;

    @PrePersist
    public void onPrePersist() {
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
        if (txnDate == null) {
            txnDate = LocalDateTime.now();
        }
    }
}
