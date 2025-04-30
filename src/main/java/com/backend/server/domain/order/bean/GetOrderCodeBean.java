package com.backend.server.domain.order.bean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetOrderCodeBean {

    private final OrderRepository orderRepository;

    public OrderEntity exec(UUID orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
