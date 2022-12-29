//package com.example.demo1_pbl4.configure;
//
//import com.example.demo1_pbl4.security.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
//    {
//        return http
//                .csrf(csrf-> csrf.ignoringAntMatchers("/h2-console/**"))
//                .authorizeRequests(auth-> auth
//                .antMatchers("/", "/login", "/register", "/logout", "/css/**", "/fonts/**", "/images/**", "/js/**", "/homepage/**").permitAll()
//                        .anyRequest().authenticated()
//                        .antMatchers("/events/**", "/posts/**").access("hasAnyRole('ADMIN','USER')")
//
//                ).formLogin(login->login.loginProcessingUrl("/j_spring_security_check").and())//Submit url
//////
//////                .defaultSuccessUrl("/events/find")
////////                .successForwardUrl("/process_login")
//////                .failureUrl("/login?error=true")
//////                .usernameParameter("username")
//////                .passwordParameter("password")
//////                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/"))
////                .formLogin()
//////
//
//                .userDetailsService(customUserDetailsService)
//                .headers(headers-> headers.frameOptions().sameOrigin())
//                .build();
//    }
//}

// Các trang còn lại cần phải Authenticate:
   //     http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().build();