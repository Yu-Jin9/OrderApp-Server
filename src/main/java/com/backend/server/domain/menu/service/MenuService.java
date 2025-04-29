package com.backend.server.domain.menu.service;

import com.backend.server.domain.menu.bean.GetMenuEntityBean;
import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.data.dto.ResponseGetMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final GetMenuEntityBean getMenuEntityBean;

    public ResponseGetMenuDto getMenu(UUID menuId) {
        MenuEntity menu = getMenuEntityBean.exec(menuId);

        if (menu == null) return null;

        return ResponseGetMenuDto.builder().menu(menu).build();

    }

}
