package com.backend.server.domain.user.bean;

import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUserEntityBean {

    private final UserRepository userRepository;

    public List<UserEntity> exec() {
        return userRepository.findAll();
    }
}
