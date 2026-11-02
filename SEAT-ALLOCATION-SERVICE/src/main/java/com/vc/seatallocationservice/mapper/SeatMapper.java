package com.vc.seatallocationservice.mapper;

import com.vc.seatallocationservice.dto.SeatRequest;
import com.vc.seatallocationservice.dto.SeatResponse;
import com.vc.seatallocationservice.entity.Seat;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Seat toEntity(SeatRequest request);
    SeatResponse toResponse(Seat seat);
}
