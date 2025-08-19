package com.mcnc.bizmob.web.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()  // CSRF 보호 비활성화 (선택 사항)
        	.headers().frameOptions().sameOrigin().and()
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            	.antMatchers("/login").permitAll() 
            	 .antMatchers("/", "/health", "/actuator/health", "/actuator/health/**").permitAll()
            	.antMatchers(HttpMethod.GET, "/health", "/swagger-ui/**", "/swagger", "/v3/**").permitAll()	// 스웨거
            	.antMatchers(HttpMethod.GET,  "/swagger-ui/**", "/swagger", "/v3/**").permitAll()	// 스웨거
            	.antMatchers(HttpMethod.POST, "/api/**").permitAll()
//            	.antMatchers(HttpMethod.POST, "/api/v1/**").hasAnyAuthority("ROLE_ADMIN")
            	.anyRequest().permitAll()
            )
            ;  
        
        return http.build();
	 }
}