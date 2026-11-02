package com.vc.seatallocationservice.dto;

import com.vc.common.dto.SeatStatus;

public record SeatResponse(
        Long id,
        Long showId,
        String seatNumber,
        SeatStatus status) {
}
