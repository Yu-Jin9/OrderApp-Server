package com.backend.server.domain.menu.controller;

import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.data.dto.RequestSaveMenuDto;
import com.backend.server.domain.menu.data.dto.ResponseGetMenuDto;
import com.backend.server.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMenu(@RequestParam("menuId")UUID menuId) {
        ResponseGetMenuDto responseGetMenuDto = menuService.getMenu(menuId);

        Map<String, Object> response = new HashMap<>();
        response.put("menuInfo", responseGetMenuDto);
        response.put("message", responseGetMenuDto == null ? "메뉴조회 실패" : "메뉴조회 성공");
        response.put("hasSuccess", responseGetMenuDto != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addMenu(@RequestBody MenuEntity menuItem) {
        RequestSaveMenuDto requestSaveMenuDto = menuService.saveMenu(menuItem);

        Map<String, Object> response = new HashMap<>();
        response.put("message", requestSaveMenuDto == null ? "메뉴추가 실패" : "메뉴추가 성공");
        response.put("hasSuccess", requestSaveMenuDto != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

