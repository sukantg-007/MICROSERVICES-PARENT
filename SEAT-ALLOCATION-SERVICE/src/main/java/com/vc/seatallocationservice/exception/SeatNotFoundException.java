package com.vc.seatallocationservice.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(Long id) {
        super("Seat not found with id: " + id);
    }
}
