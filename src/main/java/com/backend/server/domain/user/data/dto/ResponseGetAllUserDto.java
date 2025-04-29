package com.backend.server.domain.user.data.dto;

import com.backend.server.domain.user.data.Role;
import com.backend.server.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseGetAllUserDto {

    private UUID userId;
    private String email;
    private String userName;
    private Role role;

    @Builder
    public ResponseGetAllUserDto(UserEntity allUser) {
        this.userId = getUserId();
        this.email = getEmail();
        this.userName = getUserName();
        this.role = getRole();
    }

}
