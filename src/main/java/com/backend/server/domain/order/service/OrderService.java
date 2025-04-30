package com.backend.server.domain.order.service;


import com.backend.server.domain.order.bean.GetAllOrderEntityBean;
import com.backend.server.domain.order.bean.GetOrderEntityBean;

import com.backend.server.domain.order.bean.SaveOrderEntityBean;
import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import com.backend.server.domain.order.data.dto.SaveOrderDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final SaveOrderEntityBean saveOrderEntityBean;
    public UUID saveOrder(SaveOrderDto saveOrderDto) {

        OrderEntity saveOrderEntity = OrderEntity.builder().saveOrder(saveOrderDto).orderTime(LocalDateTime.now()).build();
        saveOrderEntityBean.exec(saveOrderEntity);

        OrderEntity getOrder = getOrderEntityBean.exec(saveOrderEntity.getOrderId());

        return getOrder == null ? null : getOrder.getOrderId();
    }

}
