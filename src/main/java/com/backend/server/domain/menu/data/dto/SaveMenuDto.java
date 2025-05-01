package com.backend.server.domain.menu.data.dto;

import com.backend.server.domain.menu.data.MenuEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class SaveMenuDto {

    private String name;
    private String category;
    private int price;
    private String img;

    @Builder
    public SaveMenuDto(MenuEntity menuItem) {

        this.name = menuItem.getName();
        this.category = menuItem.getCategory();
        this.price = menuItem.getPrice();
        this.img = menuItem.getImg();
    }

}
