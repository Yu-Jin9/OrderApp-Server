package com.backend.server.domain.order.service;


import com.backend.server.domain.order.bean.*;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import com.backend.server.domain.order.data.dto.SaveOrderDto;
import lombok.RequiredArgsConstructor;
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
    private final GetRecentlyOrder getRecentlyOrder;
    public UUID saveOrder(SaveOrderDto saveOrderDto) {
        OrderEntity recentlyOrder = getRecentlyOrder.exec();
        int newCode = recentlyOrder.getCode() + 1;
        OrderEntity saveOrderEntity = OrderEntity.builder().saveOrder(saveOrderDto).newCode(newCode).build();
        saveOrderEntityBean.exec(saveOrderEntity);

        OrderEntity getOrder = getOrderEntityBean.exec(saveOrderEntity.getOrderId());

        return getOrder == null ? null : getOrder.getOrderId();
    }

    private final GetOrderCodeBean getOrderCodeBean;
    public int getOrderCode(UUID orderId) {
        OrderEntity orderCode = getOrderCodeBean.exec(orderId);

        return orderCode == null ? -1 : orderCode.getCode();
    }


    private final UpdateStateEntityBean updateStateEntityBean;
    public UUID updateState(ResponseGetOrderDto getOrderDto) {

        OrderEntity orderEntity = getOrderEntityBean.exec(getOrderDto.getOrderId());
        if(orderEntity == null) return null;

        orderEntity.updateState(getOrderDto.getState());
        OrderEntity resultOrder = updateStateEntityBean.exec(orderEntity);

        return resultOrder == null ? null : resultOrder.getOrderId();
    }

}
