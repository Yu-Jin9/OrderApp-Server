package com.backend.server.domain.user.controller;

import com.backend.server.domain.user.data.dto.*;
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
import java.util.concurrent.locks.ReentrantLock;

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

    @PostMapping("/login")  // GetMapping에는 캐싱 기능이 있기 때문에 로그인은 주로 PostMapping을 사용
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserDto loginUserDto) {

        UUID result = userService.login(loginUserDto);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", result);
        response.put("message", result == null ? "로그인 실패" : "로그인 성공");
        response.put("hasSuccess", result != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/oauth/google")
    public ResponseEntity<Map<String, Object>> oauthGoogleLogin(@RequestBody OAuthLoginDto oAuthLoginDto) {
        UUID userId = userService.oauthGoogleLogin(oAuthLoginDto);
        boolean success = userId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "로그인 성공" : "로그인 실패");
        response.put("userId", success ? userId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
