package com.backend.server.domain.user.controller;

import com.backend.server.domain.user.data.dto.DeleteUserDto;
import com.backend.server.domain.user.data.dto.ResponseGetAllUserDto;
import com.backend.server.domain.user.data.dto.ResponseGetUserDto;
import com.backend.server.domain.user.data.dto.UpdateUserDto;
import com.backend.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllUsers() {

        List<ResponseGetAllUserDto> userList = userService.getAllUsers();

        Map<String, Object> response = new HashMap<>();
        response.put("userList", userList);
        response.put("message", userList.isEmpty() ? "회원 전체조회 실패" : "회원 조회 성공!");
        response.put("hasSuccess", userList != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        UUID userId = userService.updateUser(updateUserDto);

        Map<String, Object> response = new HashMap<>();
        response.put("userList", userId);
        response.put("message", userId == null ? "회원정보 수정 실패" : "회원정보 수정 성공!");
        response.put("hasSuccess", userId != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,Object>> deleteUser(@RequestBody DeleteUserDto deleteDto) {

        UUID result = userService.deleteMenu(deleteDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", result == null ? "회원 삭제 실패" : "회원 삭제 성공!");
        response.put("hasSuccess", result != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
