package com.backend.server.domain.user.bean;

import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.dto.UpdateUserDto;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserEntityBean {

    private final UserRepository userRepository;

    public void exec(UserEntity userEntity) {
         userRepository.save(userEntity);
    }
}
