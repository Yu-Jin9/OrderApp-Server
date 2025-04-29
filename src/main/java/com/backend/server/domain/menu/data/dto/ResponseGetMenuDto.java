package com.backend.server.domain.menu.data.dto;

import com.backend.server.domain.menu.data.MenuEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseGetMenuDto {

    private UUID menuId;
    private String name;
    private String category;
    private int price;
    private String img;

    @Builder
    public ResponseGetMenuDto(MenuEntity menu) {
        this.menuId = getMenuId();
        this.name = getName();
        this.category = getCategory();
        this.price = getPrice();
        this.img = getImg();
    }

}
