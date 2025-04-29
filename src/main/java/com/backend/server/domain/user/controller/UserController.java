package com.backend.server.domain.user.controller;

import com.backend.server.domain.user.data.dto.ResponseGetUserDto;
import com.backend.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.Object;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUser(@RequestParam("userId")UUID userId) {
        ResponseGetUserDto responseGetUserDto = userService.getUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("userInfo", responseGetUserDto);
        response.put("message", responseGetUserDto == null? "회원 조회 실패" : "회원 조회 성공");
        response.put("hasSuccess",responseGetUserDto != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
