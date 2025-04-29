package com.backend.server.domain.menu.bean;

import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetMenuEntityBean {

    private final MenuRepository menuRepository;

    public MenuEntity exec(UUID menuId) {
        return menuRepository.findById(menuId).orElse(null);
    }

}
