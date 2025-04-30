package com.backend.server.domain.order.controller;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import com.backend.server.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService = null;

    @GetMapping
    public ResponseEntity<Map<String,Object>> getOrder(@RequestParam("orderId")UUID orderId ) {

        ResponseGetOrderDto responseGetOrderDto = orderService.getOrder(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("orderInfo", responseGetOrderDto);
        response.put("message", responseGetOrderDto == null ? "메뉴 조회 실패" : "메뉴 조회 성공");
        response.put("hasSuccess", responseGetOrderDto != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
