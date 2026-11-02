package com.vc.showservice.dto;

import java.time.LocalDateTime;

public record ShowRequest(
        String movieName,
        LocalDateTime showTime,
        Integer totalSeats) {
}
