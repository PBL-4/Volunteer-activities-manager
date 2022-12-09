//package com.example.demo1_pbl4.configure;
//

//import com.example.demo1_pbl4.security.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource; // Sai duong dan se sai
//import java.io.File;
//
//@Configuration
//@EnableWebSecurity  //
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());

//        return authProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//        // auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
//        //Cac trang khong yeu cau login:
//        http.authorizeRequests().antMatchers("/", "/login", "/register", "/logout", "/css/**", "/fonts/**", "/images/**", "/js/**", "/homepage/**").permitAll();
//        //Trang yeu cau cphai login voi vai tro Role_user hoac Role_Admin
//        http.authorizeRequests()
//                .antMatchers("/events/**", "/posts/**").access("hasAnyRole('ADMIN','USER')");
//
//        //Trang chỉ dành cho ADMIN:
//        http.authorizeRequests().antMatchers("/admin_home").access("hasRole('ADMIN')");
//
//        //Khi người dùng đã login, với vai trò USER.
//        // Nhưng truy cập vào trang yêu cầu vai trò Admin,
//        // Ngoại lệ AccessDeniedException sẽ ném ra
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//        //Cấu hình cho Login Form
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/j_spring_security_check")//Submit url
//                .loginPage("/login")
//                .defaultSuccessUrl("/events/find")
////                .successForwardUrl("/process_login")
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
//        // Cấu hình Remember Me.
//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
//    }
//
//    // Token stored in Memory (Of Web Server).
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }
//}
