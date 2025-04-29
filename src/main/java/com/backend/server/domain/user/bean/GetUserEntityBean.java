package com.backend.server.domain.user.bean;

import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetUserEntityBean {

    private final UserRepository userRepository;

    public UserEntity exec(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }

}
