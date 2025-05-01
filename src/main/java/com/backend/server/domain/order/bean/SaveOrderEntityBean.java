package com.backend.server.domain.order.bean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveOrderEntityBean {

    private final OrderRepository orderRepository;
    public void exec(OrderEntity orderEntity) {

        orderRepository.save(orderEntity);
    }
}
