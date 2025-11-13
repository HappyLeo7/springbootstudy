package com.example.security.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.example.security.controller.LoginController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        
    	
    	System.out.println("--access denied--");
//    	response.sendRedirect("/?code=forbidden");
    	
    	//System.out.println(request.getRequestURL().toString());
    	
    	response.sendRedirect("/?error_code=no_authorization");
    	
//    	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType("application/json;charset=UTF-8");
//
//        response.getWriter().write("{\"error\": \"접근 권한이 없습니다.\"}");
    }
}