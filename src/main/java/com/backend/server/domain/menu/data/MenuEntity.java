package com.backend.server.domain.menu.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class MenuEntity {

    @Id
    private UUID menuId;
    private String name;
    private String category;
    private int price;
    private String img;
    private boolean hasDelete;


}
