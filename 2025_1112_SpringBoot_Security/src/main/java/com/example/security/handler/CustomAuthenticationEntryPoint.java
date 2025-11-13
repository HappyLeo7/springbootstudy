package com.example.security.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        System.out.println("🚫 인증 안 된 사용자 접근: " + request.getRequestURI());
        
        
        response.sendRedirect("/?error_code=no_authentication");
        // JSON 응답으로 처리 (API용)
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write("{\"error\": \"인증이 필요합니다.\"}");
    }
}
