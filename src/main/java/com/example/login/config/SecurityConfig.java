package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   /*  @Bean
    public InMemoryUserDetailsManager users(){
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build(),
            User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build()
        );
    } */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("public/**").permitAll()
            .requestMatchers("v1/home").authenticated()
            .requestMatchers("v1/admin").hasAuthority("ADMIN").anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .build();
    }    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
