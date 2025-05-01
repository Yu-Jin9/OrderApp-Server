package com.backend.server.domain.user.data.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteUserDto {

    private UUID userId;
}
