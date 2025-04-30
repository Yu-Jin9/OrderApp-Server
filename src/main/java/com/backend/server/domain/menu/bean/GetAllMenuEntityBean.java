package com.backend.server.domain.menu.bean;

import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllMenuEntityBean {

    private  final MenuRepository menuRepository;

    public List<MenuEntity> exec() {
        return menuRepository.findAllByHasDeleteFalse();
    }
}
