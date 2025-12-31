package com.example.E_commerce.App.security;

import com.example.E_commerce.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {
    @Autowired
    UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http
                .authorizeHttpRequests(auth  ->
                        auth
                                .requestMatchers("/","/login").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(form ->
                form
                        .loginPage("/login")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/dashboard")

                        .failureForwardUrl("/login?error=true")
                        .permitAll()



                        )
                .userDetailsService(userService);
        return http.build();
    }

}
