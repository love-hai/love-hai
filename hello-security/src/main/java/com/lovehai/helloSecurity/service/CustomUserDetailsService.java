package com.lovehai.helloSecurity.service;

import com.lovehai.helloSecurity.entity.UserAuthDetail;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthDetailService userAuthDetailService;

    public CustomUserDetailsService(UserAuthDetailService u) {
        this.userAuthDetailService = u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDetail user = userAuthDetailService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户未找到：" + username));
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
