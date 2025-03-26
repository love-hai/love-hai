package com.lovehai.helloSecurity.service;

import com.lovehai.helloSecurity.entity.UserAuthDetail;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthDetailService userAuthDetailService;
    private final Map<String, UserDetails> userMap = new ConcurrentHashMap<>();

    public CustomUserDetailsService(UserAuthDetailService u) {
        this.userAuthDetailService = u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userMap.get(username);
        UserAuthDetail userAuthDetail = userAuthDetailService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户未找到：" + username));
        if (null != userDetails) {
            if (userAuthDetail.getPassword().equals(userDetails.getPassword())) {
                // 修改密码
                return userDetails;
            }
        }
        UserDetails u = User.withUsername(userAuthDetail.getUsername())
                .authorities("read", "write")
                .password(userAuthDetail.getPassword())
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .roles(userAuthDetail.getRole())
                .build();
        userMap.put(username, u);
        return u;
    }
}
