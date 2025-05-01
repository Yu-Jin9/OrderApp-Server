package com.backend.server.domain.user.data.dto;

import com.backend.server.domain.user.data.Role;
import com.backend.server.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseGetUserDto {

    private UUID userId;
    private String email;
    private Role role;
    private String userName;

    @Builder
    public ResponseGetUserDto(UserEntity user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.userName = user.getUserName();
    }
}
