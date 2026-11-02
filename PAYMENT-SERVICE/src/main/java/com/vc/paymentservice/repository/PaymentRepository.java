package com.vc.paymentservice.repository;

import com.vc.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(UUID paymentId);

    Optional<Payment> findBySeatId(Long seatId);
}
