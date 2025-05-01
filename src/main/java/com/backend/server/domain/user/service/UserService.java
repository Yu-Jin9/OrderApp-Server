package com.backend.server.domain.user.service;

import com.backend.server.domain.order.data.dto.SaveOrderDto;
import com.backend.server.domain.user.bean.GetAllUserEntityBean;
import com.backend.server.domain.user.bean.GetUserEntityBean;
import com.backend.server.domain.user.bean.LoginUserEntityBean;
import com.backend.server.domain.user.bean.SaveUserEntityBean;
import com.backend.server.domain.user.data.UserEntity;
import com.backend.server.domain.user.data.UserRole;
import com.backend.server.domain.user.data.dto.*;
import com.backend.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate;

    private final GetUserEntityBean getUserEntityBean;
    public ResponseGetUserDto getUser(UUID userId) {

        UserEntity user = getUserEntityBean.exec(userId);

        if (user == null || user.isHasDelete()) return null;

        return ResponseGetUserDto.builder().user(user).build();
    }

    private final GetAllUserEntityBean getAllUserEntityBean;
    public List<ResponseGetAllUserDto> getAllUsers() {
        List<UserEntity> allUser = getAllUserEntityBean.exec();

        if (allUser == null) return null;

        return allUser.stream()
                .map(ResponseGetAllUserDto::new)
                .collect(Collectors.toList());
    }

    private final SaveUserEntityBean saveUserEntityBean;
     public UUID updateUser(UpdateUserDto updateUserDto) {
         UserEntity userEntity = getUserEntityBean.exec(updateUserDto.getUserId());
         if(userEntity == null) return null;

         userEntity.updateUser(updateUserDto);
         saveUserEntityBean.exec(userEntity);

         return userEntity.getUserId();
     }

    public UUID deleteMenu(DeleteUserDto deleteUserDto) {
        UserEntity userEntity = getUserEntityBean.exec(deleteUserDto.getUserId());
        if(userEntity == null) return null;

        userEntity.deleteUser(true);
        saveUserEntityBean.exec(userEntity);

        return userEntity.getUserId();
    }

    private final LoginUserEntityBean loginUserEntityBean;
    public UUID login(LoginUserDto loginUserDto) {
         UserEntity userEntity = loginUserEntityBean.exec(loginUserDto);
         return userEntity == null ? null : userEntity.getUserId();
    }

    public UUID oauthGoogleLogin(OAuthLoginDto oAuthLoginDto) {
        String code = oAuthLoginDto.getCode();

        String tokenUri = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add("code", code);
        tokenParams.add("client_id", clientId);
        tokenParams.add("client_secret", clientSecret);
        tokenParams.add("redirect_uri", redirectUri);
        tokenParams.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenParams, headers);

        Map tokenResponse = restTemplate.postForObject(tokenUri, tokenRequest, Map.class);
        String accessToken = (String) tokenResponse.get("access_token");

        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.setBearerAuth(accessToken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                userRequest,
                Map.class
        );

        Map userData = userResponse.getBody();
        String email = (String) userData.get("email");
        String name = (String) userData.get("name");

        UserEntity user = UserEntity.builder()
                .userId(UUID.randomUUID())
                .email(email)
                .password("passWord")
                .userName(name)
                .userRole(UserRole.USER)
                .hasDelete(false)
                .build();

        saveUserEntityBean.exec(user);

        return user.getUserId();
    }
}
