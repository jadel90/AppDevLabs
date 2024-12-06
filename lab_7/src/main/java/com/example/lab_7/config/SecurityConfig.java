package com.example.lab_7.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toH2Console()).permitAll() // Allow H2 console access
                        .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll() // Allow public GET requests
                        .requestMatchers(HttpMethod.GET, "/api/households", "/api/pets").permitAll() // No authentication for GET requests to households/pets
                        .requestMatchers(HttpMethod.GET, "/api/statistics").hasAnyRole("ADMIN", "USER") // Only Admin/User can access statistics
                        .requestMatchers(HttpMethod.POST, "/api/pets", "/api/households", "/api/users").hasRole("ADMIN") // Admin-only creation
                        .requestMatchers(HttpMethod.DELETE, "/api/pets/**", "/api/households/**", "/api/users/**").hasRole("ADMIN") // Admin-only deletion
                        .requestMatchers(HttpMethod.PATCH, "/api/pets/**", "/api/households/**").hasAnyRole("ADMIN", "USER") // Admin/User updates
                        .requestMatchers(HttpMethod.PUT, "/api/users/reset-password", "/api/users/toggle-unlocked", "/api/users").hasRole("ADMIN") // Admin-only user management
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // Allow content in frames from the same origin (for H2 console)
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Enable CSRF protection with HttpOnly=false cookies
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
                )
                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication with default settings
                .formLogin(Customizer.withDefaults()) // Enable form-based login with default settings
                .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
