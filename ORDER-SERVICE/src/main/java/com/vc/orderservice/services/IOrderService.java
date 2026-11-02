package com.vc.orderservice.services;

import com.vc.orderservice.dto.OrderRequest;
import com.vc.orderservice.dto.OrderResponse;

import java.util.List;

public interface IOrderService {
    String getOrderInfo();

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrder(Long id);

    List<OrderResponse> getAllOrders();
}
