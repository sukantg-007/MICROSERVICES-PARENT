package com.vc.orderservice.services;

import com.vc.common.dto.ShowDTO;
import com.vc.common.dto.UserDTO;
import com.vc.orderservice.client.ShowClientService;
import com.vc.orderservice.client.UserClientService;
import com.vc.orderservice.dto.OrderRequest;
import com.vc.orderservice.dto.OrderResponse;
import com.vc.orderservice.entity.Order;
import com.vc.orderservice.entity.OrderStatus;
import com.vc.orderservice.exception.OrderNotFoundException;
import com.vc.orderservice.mapper.OrderMapper;
import com.vc.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    @Value("${server.port}")
    private String serverPort;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShowClientService showService;
    private final UserClientService userService;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Creating order with request: {}", request);

        ShowDTO show= showService.getShowById(request.showId());
        if (show == null) {
            throw new RuntimeException("Show not found with id: " + request.showId());
        }
        UserDTO user = userService.getUserById(request.userId());
        if (user == null) {
            throw new RuntimeException("User not found with id: " + request.userId());
        }

        Order order = orderMapper.toEntity(request);
        order.setStatus(OrderStatus.INITIATED);
        Order saved=orderRepository.save(order);

        // ORDER CREATED EVENT PUSH TO KAFKA - STEP 1
        log.info("Created order with id: {}", saved.getId());
        return orderMapper.toResponse(saved, user, show );
    }

    @Override
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        ShowDTO show= showService.getShowById(order.getShowId());
        if (show == null) {
            throw new RuntimeException("Show not found with id: " + order.getShowId());
        }
        UserDTO user = userService.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with id: " + order.getUserId());
        }

       return orderMapper.toResponse(order, user, show );
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().
                map(this::enrichOrder)
                .toList();
    }

    @Override
    public String getOrderInfo() {
        return "Order Service Port: " + serverPort;
    }

    private OrderResponse enrichOrder(Order order) {
        return orderMapper.toResponse(
                order,
                userService.getUserById(order.getUserId()),
                showService.getShowById(order.getShowId())
        );
    }
}
