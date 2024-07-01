package com.example.travel_diary.global.config;

import com.example.travel_diary.global.utils.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable);
        security.cors(c -> c.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","OPTIONS"));
//            corsConfiguration.setAllowedOrigins(List.of("*"));
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8002", "http://localhost:8003", "http://localhost:8004", "http://localhost:8005"));

//            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }));
        security.userDetailsService(userDetailsService);

//        security.authorizeHttpRequests(req ->
//            req.requestMatchers("/api/v1/auths/signUp", "/api/v1/auths/signIn")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//        );
        security.authorizeHttpRequests(req ->
                req.requestMatchers("/api/v1/auths/signUp",
                                "/api/v1/auths/signIn",
                                "/api/v1/posts/top5/recent",
                                "/api/v1/posts/top5/like",
                                "/api/v1/posts/top5/diaries",
                                "/api/v1/posts/recent",
                                "api/v1/country/**"
                                )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );
        security.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }
}
