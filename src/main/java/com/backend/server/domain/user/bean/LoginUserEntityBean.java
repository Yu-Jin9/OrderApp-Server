package com.backend.server.domain.user.bean;

import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.dto.LoginUserDto;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginUserEntityBean {

    private final UserRepository userRepository;
    public UserEntity exec(LoginUserDto loginUserDto) {
        return userRepository.findByEmailAndPassword(loginUserDto.getPassword(), loginUserDto.getEmail());
    }
}
