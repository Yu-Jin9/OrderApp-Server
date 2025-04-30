package com.backend.server.domain.order.data;

import com.backend.server.domain.order.data.dto.SaveOrderDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class OrderEntity {

    @Id
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private STATE state;
    private int totalPrice;
    private int code;
    private LocalDateTime orderTime;
    private UUID userId;
    private int menuNum;
    @Enumerated(EnumType.STRING)
    private ORDER orderType;

    @Builder
    public OrderEntity(SaveOrderDto saveOrder, LocalDateTime orderTime, int newCode) {
        this.userId = saveOrder.getUserId();
        this.totalPrice = saveOrder.getTotalPrice();
        this.menuNum = saveOrder.getMenuNum();
        this.orderType = saveOrder.getOrderType();
        this.orderTime = orderTime;
        this.code = newCode;
    }

}
