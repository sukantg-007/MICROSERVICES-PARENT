package com.vc.seatallocationservice.controller;

import com.vc.seatallocationservice.dto.SeatRequest;
import com.vc.seatallocationservice.dto.SeatResponse;
import com.vc.seatallocationservice.services.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final ISeatService seatService;

    @PostMapping
    public ResponseEntity<SeatResponse> createSeat(
            @RequestBody SeatRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(seatService.createSeat(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeat(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getSeat(id));
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<SeatResponse>> getSeatsByShow(@PathVariable Long showId) {
        return ResponseEntity.ok(seatService.getSeatsByShow(showId));
    }

    @PutMapping("/{id}/allocate")
    public ResponseEntity<SeatResponse> allocateSeat(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.allocateSeat(id));
    }
}
