package com.app.eazy_bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws
            Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account", "/balance", "/loans", "/cards").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =User.withUsername("user").password("{noop}123").authorities("read").build();
        UserDetails adm =User.withUsername("adm").password("{noop}123").authorities("adm").build();

        return new InMemoryUserDetailsManager(user, adm);
    }
}
