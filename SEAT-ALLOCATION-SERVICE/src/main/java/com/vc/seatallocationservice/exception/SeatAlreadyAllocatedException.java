package com.vc.seatallocationservice.exception;

public class SeatAlreadyAllocatedException extends RuntimeException {
    public SeatAlreadyAllocatedException(Long id) {
        super("Seat already allocated with id: " + id);
    }
}
