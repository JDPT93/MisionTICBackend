package com.jdpt93.cycle3.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdpt93.cycle3.backend.JwtAuthorizationFilter;

@Configuration
class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.OPTIONS).permitAll()
                        .antMatchers(HttpMethod.POST, "/api/users/signup", "/api/users/signin").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

}
