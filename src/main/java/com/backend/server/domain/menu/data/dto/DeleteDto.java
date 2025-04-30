package com.backend.server.domain.menu.data.dto;

import com.backend.server.domain.menu.data.MenuEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class DeleteDto {

    private UUID menuId;

    @Builder
    public DeleteDto(MenuEntity menuEntity) {
        this.menuId = menuEntity.getMenuId();
    }
}
