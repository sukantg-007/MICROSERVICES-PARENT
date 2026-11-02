package com.vc.orderservice.dto;

import com.vc.common.dto.ShowDTO;
import com.vc.common.dto.UserDTO;
import com.vc.orderservice.entity.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
public record OrderResponse(
        Long id,
        UserDTO user,
        ShowDTO show,
        BigDecimal amount,
        OrderStatus status,
        LocalDateTime createdAt
) {}
