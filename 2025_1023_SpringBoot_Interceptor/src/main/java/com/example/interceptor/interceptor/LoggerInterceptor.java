package com.example.interceptor.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============================================");
        log.info("==================== BEGIN ====================");
        log.info("Request URI ===> " + request.getRequestURI());
        
        //로그인 정보 얻어오기
        Map<String,Object> user =(Map<String, Object>) request.getSession().getAttribute("user");
        if(user==null) {
        	response.sendRedirect("/?reason=session_timeout");
        	return false;
        }
        
        String uri=request.getRequestURI();
        //관리자체크
        if(uri.startsWith("/admin")) { //  "/admin"으로 시작되는 문자열이면
        	if(user.get("grade").equals("일반")) {
        		response.sendRedirect("/?reason=not_admin");
        	}
        }
        
        //성인체크
        if(uri.startsWith("/adult")) {
        	int age=Integer.parseInt((String) user.get("age"));
//        	if(  (int)(user.get("age")) <19) {
        		if(  age<19) {
        		System.out.println("	[성인체크]");
        		response.sendRedirect("/?reason=not_adult");
        	}
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("==================== END ======================");
        log.info("===============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}