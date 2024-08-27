package com.codewithmeet.elearningplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("GUEST", "ADMIN" , "TEACHER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("ADMIN" , "TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("ADMIN" , "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("ADMIN" , "TEACHER")
//                        .requestMatchers("/all").permitAll()
                        .anyRequest()
                        .authenticated());

//        defaults login page
//        httpSecurity.formLogin(Customizer.withDefaults());
//        defaults postman in basic auth
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.formLogin(form -> {
           form.loginPage("/login-page");
           form.loginProcessingUrl("/login-process");
           form.usernameParameter("username");
           form.passwordParameter("password");
           form.defaultSuccessUrl("/success");
           form.failureForwardUrl("/login?logout");
        });

        httpSecurity.logout(log->{
//            log.logoutUrl("/logout-page");
            log.logoutSuccessUrl("/login-page");
        });


        return httpSecurity.build();
    }

}
