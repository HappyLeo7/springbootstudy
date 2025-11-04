package com.example.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAdvice {

    //Spring에게 Injection시켜달라고 요청
    @Autowired
    HttpServletRequest request;

    // Target(감시지점) 설정
    //   *(..) : 모든메소드(.. <-인자구분없다)
    @Pointcut("execution(* com.example.aop.service.*Service.*())")
    public void service_pointcut(){}
//    @Pointcut("execution(* com.example.aop.service.*Service.*())")
//    public void service_ck(){}
    


   
    @Before("service_pointcut()")
    public void before(JoinPoint jp){

        Signature s = jp.getSignature(); //매서드의 패키지 위치정보
        System.out.println("	--Before : "  + s.toShortString()); // 패키지 정보가 짧게오고
        System.out.println("	--Before : "  + s.toLongString()); // 패키지 정보가 길게온다.
        System.out.println("	--Before : "  + s.toString()); // 

        long start = System.currentTimeMillis();
        //request binding 
        request.setAttribute("start", start);

    }

    @After("service_pointcut()")
    public void after(JoinPoint jp){

        Signature s = jp.getSignature();
        System.out.println("--After : "  + s.toShortString());


        long start = (Long)request.getAttribute("start");

        long end = System.currentTimeMillis();


        System.out.printf("--[수행시간] : %d(ms)\n", end - start);

    }
}
