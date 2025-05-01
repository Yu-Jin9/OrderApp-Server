package com.backend.server.global.config;

import com.backend.server.domain.user.data.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth
                                // Swagger 접근 가능
                                .requestMatchers(
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html",
                                        "/swagger-ui/**"
                                ).permitAll()
                                // 누구나 가능
                                .requestMatchers("/").permitAll()
                                .requestMatchers( "/api/user/google").permitAll()
                                .requestMatchers( "/api/user/oauth/google").permitAll()
                                .requestMatchers("/api/user/login").permitAll()
                                // 유저, 관리자 모두 가능
                                .requestMatchers(HttpMethod.GET, "/api/user").hasAnyRole(UserRole.USER.getValue(), UserRole.ADMIN.getValue())
                                .requestMatchers(HttpMethod.GET, "/api/menu/all").hasAnyRole(UserRole.USER.getValue(), UserRole.ADMIN.getValue())
                                // 유저만 가능
                                .requestMatchers(HttpMethod.POST, "/api/order").hasRole(UserRole.USER.getValue())
                                .requestMatchers(HttpMethod.GET, "/api/order/code").hasRole(UserRole.USER.getValue())
                                // 관리자만 가능
                                .requestMatchers("/api/user/**").hasRole(UserRole.ADMIN.getValue())
                                .requestMatchers("/api/order/**").hasRole(UserRole.ADMIN.getValue())
                                .requestMatchers("/api/menu/**").hasRole(UserRole.ADMIN.getValue())
                                .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
