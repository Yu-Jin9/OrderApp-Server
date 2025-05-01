package com.backend.server.domain.user.data.dto;

import com.backend.server.domain.user.data.UserRole;
import com.backend.server.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseGetAllUserDto {

    private UUID userId;
    private String email;
    private String userName;
    private UserRole userRole;

    @Builder
    public ResponseGetAllUserDto(UserEntity allUser) {
        this.userId = allUser.getUserId();
        this.email = allUser.getEmail();
        this.userName = allUser.getUserName();
        this.userRole = allUser.getUserRole();
    }

}
