package com.vc.seatallocationservice.services;

import com.vc.common.dto.SeatStatus;
import com.vc.seatallocationservice.dto.SeatRequest;
import com.vc.seatallocationservice.dto.SeatResponse;
import com.vc.seatallocationservice.entity.Seat;
import com.vc.seatallocationservice.exception.SeatAlreadyAllocatedException;
import com.vc.seatallocationservice.exception.SeatNotFoundException;
import com.vc.seatallocationservice.mapper.SeatMapper;
import com.vc.seatallocationservice.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements ISeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Override
    public SeatResponse createSeat(SeatRequest request) {
        log.info("Creating seat with request: {}", request);
        Seat seat = seatMapper.toEntity(request);
        seat.setStatus(SeatStatus.ALLOCATED);
        return seatMapper.toResponse(seatRepository.save(seat));
    }

    @Override
    public SeatResponse getSeatById(Long id) {
        Seat existSeat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException(id));
        return seatMapper.toResponse(existSeat);
    }


    @Override
    public List<SeatResponse> getSeatsByShow(Long showId) {
        return seatRepository.findByShowId(showId).stream()
                .map(seatMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public SeatResponse allocateSeat(Long id) {
        Seat seat = find(id);
        if (seat.getStatus() == SeatStatus.ALLOCATED) {
            throw new SeatAlreadyAllocatedException(id);
        }
        seat.setStatus(SeatStatus.ALLOCATED);
        return seatMapper.toResponse(seatRepository.save(seat));
    }

    private Seat find(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(id));
    }
}
