package com.backend.server.domain.menu.service;

import com.backend.server.domain.menu.bean.GetAllMenuEntityBean;
import com.backend.server.domain.menu.bean.GetMenuEntityBean;
import com.backend.server.domain.menu.bean.SaveMenuEntityBean;
import com.backend.server.domain.menu.data.MenuEntity;
import com.backend.server.domain.menu.data.dto.ResponseGetMenuDto;
import com.backend.server.domain.menu.data.dto.SaveMenuDto;
import com.backend.server.domain.menu.data.dto.UpdateMenuDto;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final GetMenuEntityBean getMenuEntityBean;
    public ResponseGetMenuDto getMenu(UUID menuId) {
        MenuEntity menu = getMenuEntityBean.exec(menuId);

        if (menu == null || menu.isHasDelete()) return null;

        return ResponseGetMenuDto.builder().menu(menu).build();

    }

    private final SaveMenuEntityBean saveMenuEntityBean;
    public UUID saveMenu(SaveMenuDto menuItem) {
        MenuEntity menuEntity = MenuEntity.builder().dto(menuItem).build();
        saveMenuEntityBean.exec(menuEntity);

        MenuEntity getMenu = getMenuEntityBean.exec(menuEntity.getMenuId());
        return getMenu == null ? null : menuEntity.getMenuId();
    }

    public UUID updateMenu(UpdateMenuDto menuItem) {

        MenuEntity menu = getMenuEntityBean.exec(menuItem.getMenuId());

        if (menu == null) return null;

        menu.updateMenu(menuItem);
        saveMenuEntityBean.exec(menu);

        return menu.getMenuId();

    }

    private final GetAllMenuEntityBean getAllMenuEntityBean;
    public List<ResponseGetMenuDto> getAllMenu() {
        List<MenuEntity> allMenu = getAllMenuEntityBean.exec();
        if(allMenu.isEmpty()) return Collections.emptyList();

        return allMenu.stream()
                .map(ResponseGetMenuDto::new)
                .collect(Collectors.toList());

    }

}
