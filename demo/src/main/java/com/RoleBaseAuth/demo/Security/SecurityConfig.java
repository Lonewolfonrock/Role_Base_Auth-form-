package com.RoleBaseAuth.demo.Security;

import com.RoleBaseAuth.demo.Service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ConditionalOnProperty(value = "security.enabled", havingValue = "true")
public class SecurityConfig {

    @Autowired
    private UserDetail userDetail;
    @Autowired
    private UnauthorizedEntrypoint unauthorizedEntrypoint;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
    http.csrf(httpSecurity -> httpSecurity.disable());

    http.securityMatcher("/admin-page","index","/contact","register")
            .authorizeHttpRequests(
                    (authorized) -> authorized
                            .requestMatchers("/index").hasAnyRole("USER","ADMIN")
                            .requestMatchers("/admin-page").hasRole("ADMIN")
                            .requestMatchers("/contact").permitAll()
                            .requestMatchers("/register").permitAll()
                            .anyRequest().authenticated())
            .httpBasic(httpSec -> httpSec.authenticationEntryPoint(unauthorizedEntrypoint)).userDetailsService(userDetail);
            return  http.build();
    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//    }


}

