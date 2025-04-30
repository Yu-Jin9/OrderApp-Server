package com.backend.server.domain.order.service;


import com.backend.server.domain.order.bean.GetOrderEntityBean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final GetOrderEntityBean getOrderEntityBean;
    public ResponseGetOrderDto getOrder(UUID orderId) {
        OrderEntity order = getOrderEntityBean.exec(orderId);

        if(order == null ) return null;

        return ResponseGetOrderDto.builder().order(order).build();
    }
}
