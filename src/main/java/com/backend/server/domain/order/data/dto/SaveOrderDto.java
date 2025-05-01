package com.backend.server.domain.order.data.dto;

import com.backend.server.domain.order.data.ORDER;
import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.STATE;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SaveOrderDto {

    private UUID userId;
    private int totalPrice;
    private ORDER orderType;
    private int menuNum;
    private LocalDateTime orderTime;
    private UUID orderId;
    private STATE state;

    @Builder
    public SaveOrderDto(OrderEntity saveOrder) {
        this.userId = saveOrder.getUserId();
        this.totalPrice = saveOrder.getTotalPrice();
        this.orderType = saveOrder.getOrderType();
        this.menuNum = saveOrder.getMenuNum();
        this.orderId = saveOrder.getOrderId();
        this.state = saveOrder.getState();
    }
}
