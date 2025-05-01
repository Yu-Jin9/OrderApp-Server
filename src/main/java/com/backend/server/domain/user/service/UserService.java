package com.backend.server.domain.user.service;

import com.backend.server.domain.order.data.dto.SaveOrderDto;
import com.backend.server.domain.user.bean.GetAllUserEntityBean;
import com.backend.server.domain.user.bean.GetUserEntityBean;
import com.backend.server.domain.user.bean.LoginUserEntityBean;
import com.backend.server.domain.user.bean.SaveUserEntityBean;
import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.dto.*;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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

        if (user == null || user.isHasDelete()) return null;

        return ResponseGetUserDto.builder().user(user).build();
    }

    private final GetAllUserEntityBean getAllUserEntityBean;
    public List<ResponseGetAllUserDto> getAllUsers() {
        List<UserEntity> allUser = getAllUserEntityBean.exec();

        if (allUser == null) return null;

        return allUser.stream()
                .map(ResponseGetAllUserDto::new)
                .collect(Collectors.toList());
    }

    private final SaveUserEntityBean saveUserEntityBean;
     public UUID updateUser(UpdateUserDto updateUserDto) {
         UserEntity userEntity = getUserEntityBean.exec(updateUserDto.getUserId());
         if(userEntity == null) return null;

         userEntity.updateUser(updateUserDto);
         saveUserEntityBean.exec(userEntity);

         return userEntity.getUserId();
     }

    public UUID deleteMenu(DeleteUserDto deleteUserDto) {
        UserEntity userEntity = getUserEntityBean.exec(deleteUserDto.getUserId());
        if(userEntity == null) return null;

        userEntity.deleteUser(true);
        saveUserEntityBean.exec(userEntity);

        return userEntity.getUserId();
    }

    private final LoginUserEntityBean loginUserEntityBean;
    public UUID login(LoginUserDto loginUserDto) {
         UserEntity userEntity = loginUserEntityBean.exec(loginUserDto);
         return userEntity == null ? null : userEntity.getUserId();
    }
}
