package com.vc.seatallocationservice.services;

import com.vc.seatallocationservice.dto.SeatRequest;
import com.vc.seatallocationservice.dto.SeatResponse;

import java.util.List;

public interface ISeatService {
    SeatResponse createSeat(SeatRequest request);

    SeatResponse getSeatById(Long id);

    List<SeatResponse> getSeatsByShow(Long showId);

    SeatResponse allocateSeat(Long id);
}
