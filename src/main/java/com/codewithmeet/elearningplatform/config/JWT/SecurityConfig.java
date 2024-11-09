package com.codewithmeet.elearningplatform.config.JWT;

import com.codewithmeet.elearningplatform.config.AppConstants;
import com.codewithmeet.elearningplatform.config.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug=true)
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    private AuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;


    public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/v3/api-docs/**" , "/swagger-ui/**" , "/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/auth/login", "/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN").anyRequest()
                        .authenticated());


        httpSecurity.sessionManagement(e-> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


//        defaults login page
//        httpSecurity.formLogin(Customizer.withDefaults());

//        defaults postman in basic auth
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.httpBasic(http-> http.authenticationEntryPoint(authenticationEntryPoint));





        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

//
//        httpSecurity.formLogin(form -> {
//            form.loginPage("/login-page");
//            form.loginProcessingUrl("/login-process");
//            form.usernameParameter("username");
//            form.passwordParameter("password");
//            form.defaultSuccessUrl("/success");
//            form.failureForwardUrl("/login?logout");
//        });

//        httpSecurity.logout(log -> {
//            log.logoutUrl("/logout");
//            log.logoutSuccessUrl("/login-page");
//        });

//
        httpSecurity.exceptionHandling((ex->{
            ex.authenticationEntryPoint(authenticationEntryPoint);
        }));

        return httpSecurity.build();
    }




}
