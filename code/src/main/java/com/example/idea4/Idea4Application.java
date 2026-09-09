package com.example.idea4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;

import java.io.IOException;

@SpringBootApplication
public class Idea4Application {

    public static void main(String[] args) {
        SpringApplication.run(Idea4Application.class, args);
    }

}

@Configuration
class SecurityConfiguration {

//
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) {
        return security
                .authorizeHttpRequests(a -> a.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    //@Bean
    Customizer<HttpSecurity> httpSecurityCustomizer() {
        return http -> http
                .oneTimeTokenLogin(ott -> ott.tokenGenerationSuccessHandler(new OneTimeTokenGenerationSuccessHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {

                    }
                }));
    }
}