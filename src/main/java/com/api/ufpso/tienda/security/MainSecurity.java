package com.api.ufpso.tienda.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MainSecurity  {

    private final String[] ROUTES_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/register",
            "/auth/login",
    };

    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable();
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(ROUTES_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                .anyRequest().permitAll());
        return http.build();
    }


}
