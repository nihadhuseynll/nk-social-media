package com.nkcode.nksocialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/auth/registration").permitAll()
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails firstUser = User.withUsername("Ilkin")
                .password("{noop}Ilkin2258.")
                .roles("USER")
                .build();

        UserDetails secondUser = User.withUsername("Eltun")
                .password("{noop}Eltun2258.")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(firstUser, secondUser);
    }
}
