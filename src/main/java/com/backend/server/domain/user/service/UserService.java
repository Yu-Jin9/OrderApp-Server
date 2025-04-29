package com.backend.server.domain.user.service;

import com.backend.server.domain.user.bean.GetUserEntityBean;
import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.dto.ResponseGetUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GetUserEntityBean getUserEntityBean;
    public ResponseGetUserDto getUser(UUID userId) {

        UserEntity user = getUserEntityBean.exec(userId);

        if (user == null) return null;

        return ResponseGetUserDto.builder().user(user).build();
    }

}
