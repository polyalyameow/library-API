package com.pover.Library;

import com.pover.Library.JWT.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login", "/user/login").permitAll()
//                        .requestMatchers("/loans/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_LIBRARIAN")
//                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_LIBRARIAN")
                        //.requestMatchers("/user/profile").hasAuthority("ROLE_USER")
                        .requestMatchers("/admin/**").authenticated()
                                .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tillåt alla endpoints
                        .allowedOrigins("http://127.0.0.1:5501") // Tillåt frontend-porten
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Tillåtna metoder
                        .allowedHeaders("*") // Tillåtna headers
                        .allowCredentials(true); // Tillåt cookies om behövs
            }
        };
    }
}
