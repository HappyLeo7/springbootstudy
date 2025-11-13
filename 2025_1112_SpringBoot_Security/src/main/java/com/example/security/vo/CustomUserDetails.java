package com.example.security.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

public class CustomUserDetails implements UserDetails {

	@Getter
    MemberVo user; // 

    public CustomUserDetails(MemberVo user) {
        this.user = user;
    }

    public String getMem_name() {
        return user.getMem_name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> user.getMem_grade());
    }

    @Override
    public String getPassword() {
    	//System.out.println(user.getMem_pwd());
        return user.getMem_pwd();
    }

    @Override
    public String getUsername() {
        return user.getMem_id();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
