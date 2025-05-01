package com.backend.server.domain.user.data;

import com.backend.server.domain.user.data.dto.UpdateUserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Entity
@Getter
@Builder
public class UserEntity {

    @Id
    private UUID userId;
    private String email;
    @Enumerated(EnumType.STRING)    // 기본형이 EnumType.ORDINAL -> 숫자형
    private UserRole userRole;
    private String password;
    private String userName;
    private boolean hasDelete;

    public void updateUser(UpdateUserDto updateUserDto) {
        this.userName = updateUserDto.getUserName();
        this.userRole = updateUserDto.getUserRole();
    }

    public void deleteUser(boolean result ) {
        this.hasDelete = result;
    }
}
