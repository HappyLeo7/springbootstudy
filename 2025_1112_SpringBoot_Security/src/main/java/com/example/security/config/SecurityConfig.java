package com.example.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.handler.CustomAccessDeniedHandler;
import com.example.security.handler.CustomAuthenticationEntryPoint;
import com.example.security.handler.CustomAuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/","/login_form.do", "/css/**", "/js/**", "/images/**").permitAll() //.permitAll 경로 허락하겠다 라는 의미
	            .requestMatchers("/WEB-INF/**","/favicon.ico","/error").permitAll()  // 추가
	            .requestMatchers("/admin/**").hasRole("관리자") // .hasRole("ADMIN") admin 권한을 갖는 유저만 볼수 있다
	            .anyRequest().authenticated()) // 나머지 모든경로는 로그인 해야 들어올수 있다
	        .formLogin(form -> form
	            .loginPage("/login_form.do") //로그인 폼 띄우는 경로
	            .loginProcessingUrl("/login.do") //로그인 폼에서 form 태그 action 경로
	            .defaultSuccessUrl("/",true) // 성공시 이동 경로
	            //.failureUrl("/login_form.do?error=true") // 실패했을때 돌아갈 경로와 보낼 파라메터 추가 가능
	            .failureHandler(new CustomAuthenticationFailureHandler())
	            .permitAll())
	        .logout(logout -> logout
	            .logoutUrl("/logout")
//	            .logoutSuccessUrl("/login?logout=true")
	            .logoutSuccessUrl("/")
	            .permitAll())
	    
	    	.exceptionHandling(ex -> ex
	    			.authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증 안된 사용자
	    			.accessDeniedHandler(new CustomAccessDeniedHandler()) // 권한 부족한 사용자
	    			);
	    	

	    return http.build();
	}

    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
