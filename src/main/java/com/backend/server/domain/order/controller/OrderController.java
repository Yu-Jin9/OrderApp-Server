package com.backend.server.domain.order.controller;

import com.backend.server.domain.order.data.OrderEntity;
import com.backend.server.domain.order.data.dto.ResponseGetOrderDto;
import com.backend.server.domain.order.data.dto.SaveOrderDto;
import com.backend.server.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        response.put("message", responseGetOrderDto == null ? "주문 조회 실패" : "주문 조회 성공");
        response.put("hasSuccess", responseGetOrderDto != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllOrder() {

        List<ResponseGetOrderDto> orderList = orderService.getAllOrder();

        Map<String, Object> response = new HashMap<>();
        response.put("orderList", orderList);
        response.put("message", orderList.isEmpty() ? "주문 전체조회 실패" : "주문 조회 성공!");
        response.put("hasSuccess", orderList != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> saveOrder(@RequestBody SaveOrderDto saveOrderDto) {

        UUID orderId = orderService.saveOrder(saveOrderDto);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderId);
        response.put("message", orderId == null ? "주문 실패!" : "주문 완료!");
        response.put("hasSuccess", orderId != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
