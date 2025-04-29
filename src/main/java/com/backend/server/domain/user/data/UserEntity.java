package com.backend.server.domain.user.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;


@Entity
public class UserEntity {

    @Id
    private UUID userId;
    private String email;
    @Enumerated(EnumType.STRING)    // 기본형이 EnumType.ORDINAL -> 숫자형
    private Role role;
    private String password;
    private String userName;
    private boolean hasDelete;

}
