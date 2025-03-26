package com.lovehai.helloSecurity.config;

import com.lovehai.helloSecurity.service.CustomUserDetailsService;
import com.lovehai.helloSecurity.service.UserAuthDetailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author xiahaifeng
 */

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.permitAll())
                .build();
    }

/*    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    protected UserDetailsService inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        String generatedPassword = "xiahaifeng";
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode(generatedPassword))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    protected UserDetailsService jdbcUserDetailsService(UserAuthDetailService userAuthDetailService) {
        return new CustomUserDetailsService(userAuthDetailService);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("xhf383818");
        System.out.println(encode);
    }
}