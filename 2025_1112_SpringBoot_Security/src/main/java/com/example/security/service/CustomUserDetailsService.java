package com.example.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.dao.MemberDao;
import com.example.security.vo.CustomUserDetails;
import com.example.security.vo.MemberVo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberDao member_dao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	
    	System.out.println(username);
    	
    	MemberVo  user = member_dao.selectOneFromId(username);
    	System.out.println(user);
    	if(user==null)
    	   throw new UsernameNotFoundException("ID가 틀립니다");
    	
    	
    	//환경설정 : .requestMatchers("/admin/**").hasRole("ADMIN")
    	//           ROLE_ + ADMIN 
    	if(user.getMem_grade().equals("관리자"))
    		user.setMem_grade("ROLE_ADMIN"); // 스프링이 받을때 사용하는 키워드
    	else
    		user.setMem_grade("ROLE_USER");
    	
    	
        String encodePwd = passwordEncoder.encode( user.getMem_pwd() );
        user.setMem_pwd(encodePwd);
        
        return new CustomUserDetails(user);

		/*
		 * System.out.println("--------------------"); System.out.println(username);
		 * System.out.println(encodePwd); System.out.println("--------------------");
		 * 
		 * return org.springframework.security.core.userdetails.User.builder()
		 * .username(username) .password(encodePwd) // BCrypt 암호화된 비밀번호
		 * .roles(user.getMem_grade()) // ROLE_USER, ROLE_ADMIN 등 .build();
		 */
    }

}