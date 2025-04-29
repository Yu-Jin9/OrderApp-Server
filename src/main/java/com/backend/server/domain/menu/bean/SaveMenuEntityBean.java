package com.backend.server.domain.menu.bean;

import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMenuEntityBean {

    private final MenuRepository menuRepository;

    public MenuEntity exec(MenuEntity meuItem) {
        return menuRepository.save(meuItem);
    }
}
