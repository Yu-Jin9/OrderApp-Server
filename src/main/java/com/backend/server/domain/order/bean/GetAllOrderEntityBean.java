package com.backend.server.domain.order.bean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllOrderEntityBean {

    private final OrderRepository orderRepository;

    public List<OrderEntity> exec() {
        return orderRepository.findAllByOrderByOrderTimeDesc();
    }

}
