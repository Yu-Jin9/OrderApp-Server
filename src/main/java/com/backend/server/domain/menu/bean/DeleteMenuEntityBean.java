package com.backend.server.domain.menu.bean;

import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.data.dto.DeleteDto;
import com.backend.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteMenuEntityBean {

    private final MenuRepository menuRepository;

    public void exec(MenuEntity menuEntity) {

        menuRepository.save(menuEntity);
    }

}
