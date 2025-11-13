package com.example.security.handler;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
                                        throws IOException, ServletException {

        // 예: 에러 로그 출력
        System.out.println("로그인 실패: " + exception.getMessage());

        // 예외 타입에 따라 분기 처리 가능
//        String errorMessage;
        String error_code="no_other";
        if (exception instanceof UsernameNotFoundException) {
//          errorMessage = "아이디가 존재하지 않습니다.";
          error_code = "no_username";
        }else if (exception instanceof BadCredentialsException) {
//            errorMessage = "비밀번호가 올바르지 않습니다.";
            error_code = "no_password";
        }
        

        // 에러 메시지를 쿼리 파라미터로 전달
        response.sendRedirect("/login_form.do?error=true&error_code=" + 
            java.net.URLEncoder.encode(error_code, "UTF-8"));
    }
}
