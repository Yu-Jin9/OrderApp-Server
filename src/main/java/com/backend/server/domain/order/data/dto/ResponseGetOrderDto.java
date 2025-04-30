package com.backend.server.domain.order.data.dto;

import com.backend.server.domain.order.data.ORDER;
import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.STATE;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ResponseGetOrderDto {

    private UUID orderId;
    private UUID userId;
    private int totlaPrice;
    private STATE state;
    private int code;
    private int menuNum;
    private LocalDateTime orderTime;
    private ORDER orderType;

    @Builder
    public ResponseGetOrderDto(OrderEntity order) {
        this.orderId = getOrderId();
        this.userId = getUserId();
        this.totlaPrice = getTotlaPrice();
        this.state = getState();
        this.code = getCode();
        this.menuNum = getMenuNum();
        this.orderTime = getOrderTime();
        this.orderType = getOrderType();
    }
}
