package com.vc.seatallocationservice.dto;

import lombok.Builder;

@Builder
public record SeatRequest(
        Long showId,
        String seatNumber) {
}
