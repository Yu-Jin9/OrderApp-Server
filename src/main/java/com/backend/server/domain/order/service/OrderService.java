package com.backend.server.domain.order.service;


import com.backend.server.domain.order.bean.GetAllOrderEntityBean;
import com.backend.server.domain.order.bean.GetOrderEntityBean;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final GetOrderEntityBean getOrderEntityBean;
    public ResponseGetOrderDto getOrder(UUID orderId) {
        OrderEntity order = getOrderEntityBean.exec(orderId);

        if(order == null ) return null;

        return ResponseGetOrderDto.builder().order(order).build();
    }

    private final GetAllOrderEntityBean getAllOrderEntityBean;
    public List<ResponseGetOrderDto> getAllOrder() {
        List<OrderEntity> allOrder = getAllOrderEntityBean.exec();

        if(allOrder.isEmpty()) return null;

        return allOrder.stream()
                .map(ResponseGetOrderDto::new)
                .collect(Collectors.toList());
    }
}
