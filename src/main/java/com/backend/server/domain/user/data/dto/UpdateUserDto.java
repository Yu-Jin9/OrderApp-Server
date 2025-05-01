package com.backend.server.domain.user.data.dto;

import com.backend.server.domain.user.data.UserRole;
import com.backend.server.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UpdateUserDto {
    private UUID userId;
    private String userName;
    private UserRole userRole;

    @Builder
    public UpdateUserDto(UserEntity updateUserEntity) {
        this.userName = updateUserEntity.getUserName();
        this.userRole = updateUserEntity.getUserRole();
    }

}
