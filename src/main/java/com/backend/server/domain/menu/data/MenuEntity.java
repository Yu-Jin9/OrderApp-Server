package com.backend.server.domain.menu.data;

import com.backend.server.domain.menu.data.dto.SaveMenuDto;
import com.backend.server.domain.menu.data.dto.UpdateMenuDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;

import java.util.UUID;

@Entity
@Getter
public class MenuEntity {

    @Id
    private UUID menuId;
    private String name;
    private String category;
    private int price;
    private String img;
    private boolean hasDelete;


    public void updateMenu(UpdateMenuDto menuItem) {
        name = menuItem.getName();
        category = menuItem.getCategory();
        price = menuItem.getPrice();
        img = menuItem.getImg();
    }

    @Builder
    public MenuEntity(SaveMenuDto dto) {
        this.name = dto.getName();
        this.category = dto.getCategory();
        this.price = dto.getPrice();
        this.img = dto.getImg();
    }

}
