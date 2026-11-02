package com.vc.seatallocationservice.entity;

import com.vc.common.dto.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long showId;

    @Column(nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @PrePersist
    void onCreate() {
        if (status == null) {
            status = SeatStatus.AVAILABLE;
        }
    }
}
