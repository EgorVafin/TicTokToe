package org.tictactoe.config;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests()
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/**").authenticated()
                .and()
                .authorizeHttpRequests()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")//TODO не работает с токеном
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
