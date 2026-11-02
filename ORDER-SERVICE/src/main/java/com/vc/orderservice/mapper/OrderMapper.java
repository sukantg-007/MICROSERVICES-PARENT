package com.vc.orderservice.mapper;

import com.vc.common.dto.ShowDTO;
import com.vc.common.dto.UserDTO;
import com.vc.orderservice.dto.OrderRequest;
import com.vc.orderservice.dto.OrderResponse;
import com.vc.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "show", source = "showDto")
    @Mapping(target = "amount", source = "order.amount")
    @Mapping(target = "status", source = "order.status")
    @Mapping(target = "createdAt", source = "order.createdAt")
    OrderResponse toResponse(Order order, UserDTO userDto, ShowDTO showDto);

    Order toEntity(OrderRequest orderRequest);

}
