package com.vc.common.dto;


import java.time.LocalDateTime;

public record ShowDTO(
        Long id,
        String movieName,
        ShowStatus status,
        LocalDateTime showTime,
        Integer totalSeats,
        Integer availableSeats
) {
}

