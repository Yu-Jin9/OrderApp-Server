package com.backend.server.domain.menu.controller;

import com.backend.server.domain.menu.data.dto.DeleteDto;
import com.backend.server.domain.menu.data.dto.SaveMenuDto;
import com.backend.server.domain.menu.data.dto.ResponseGetMenuDto;
import com.backend.server.domain.menu.data.dto.UpdateMenuDto;
import com.backend.server.domain.menu.service.MenuService;
import com.backend.server.domain.user.data.dto.ResponseGetAllUserDto;
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
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService = null;

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
    public ResponseEntity<Map<String, Object>> addMenu(@RequestBody SaveMenuDto menuItem) {

        UUID menuId = menuService.saveMenu(menuItem);

        Map<String, Object> response = new HashMap<>();
        response.put("menuId", menuId);
        response.put("message", menuId == null ? "메뉴추가 실패" : "메뉴추가 성공");
        response.put("hasSuccess", menuId != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateMenu(@RequestBody UpdateMenuDto menuItem) {

        UUID menuId = menuService.updateMenu(menuItem);

        Map<String, Object> response = new HashMap<>();
        response.put("menuId", menuId);
        response.put("message", menuId == null ? "메뉴 업데이트 실패" : "메뉴 업데이트 완료");
        response.put("hasSuccess", menuId != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllmenu() {

        List<ResponseGetMenuDto> menuList = menuService.getAllMenu();

        Map<String, Object> response = new HashMap<>();
        response.put("menuList", menuList);
        response.put("message", menuList.isEmpty() ? "메뉴 전체조회 실패" : "메뉴 조회 성공!");
        response.put("hasSuccess", menuList != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteMenu(@RequestBody DeleteDto deleteDto) {

        UUID result = menuService.deleteMenu(deleteDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", result == null ? "메뉴 삭제 실패" : "메뉴 삭제 성공!");
        response.put("hasSuccess", result != null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

