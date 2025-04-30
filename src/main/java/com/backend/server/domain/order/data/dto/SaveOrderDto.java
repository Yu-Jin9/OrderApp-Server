package com.backend.server.domain.order.data.dto;

import com.backend.server.domain.order.data.ORDER;
import com.backend.server.domain.order.data.OrderEntity;
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

    @Builder
    public SaveOrderDto(OrderEntity saveOrder) {
        this.userId = getUserId();
        this.totalPrice = getTotalPrice();
        this.orderType = getOrderType();
        this.menuNum = getMenuNum();
        this.orderId = getOrderId();
    }
}
