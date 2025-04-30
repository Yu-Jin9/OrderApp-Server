package com.backend.server.domain.order.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
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

}
