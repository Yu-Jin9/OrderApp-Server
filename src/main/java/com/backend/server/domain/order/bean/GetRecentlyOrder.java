package com.backend.server.domain.order.bean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GetRecentlyOrder {

    private final OrderRepository orderRepository;

    public OrderEntity exec() {
        return orderRepository.findFirstByOrderByOrderTimeDesc().orElse(null);
    }
}
