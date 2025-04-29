package com.backend.server.domain.user.service;

import com.backend.server.domain.user.bean.GetAllUserEntityBean;
import com.backend.server.domain.user.bean.GetUserEntityBean;
import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.dto.ResponseGetAllUserDto;
import com.backend.server.domain.user.data.dto.ResponseGetUserDto;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GetUserEntityBean getUserEntityBean;
    public ResponseGetUserDto getUser(UUID userId) {

        UserEntity user = getUserEntityBean.exec(userId);

        if (user == null) return null;

        return ResponseGetUserDto.builder().user(user).build();
    }

    private final GetAllUserEntityBean getAllUserEntityBean;
    public List<ResponseGetAllUserDto> getAllUsers() {
        List<UserEntity> allUser = getAllUserEntityBean.exec();

        if (allUser == null) return null;

        return allUser.stream()
                .map(user -> new ResponseGetAllUserDto(user))
                .collect(Collectors.toList());
    }
}
