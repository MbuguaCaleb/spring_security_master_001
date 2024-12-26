package com.safaricom.microservices.fileupload.ssm_2025_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


//Configuration classes are injected into the Spring Context During application StartUp
//As My application starts Up, I will have this Spring Security Config
@Configuration
public class SecurityConfig {

    //HttpSecurity is already autoconfigured by SpringBoot
    //we inject the preconfigured HttpSecurity bean out of the Box
    //with the HttpSecurity we Build the filter which endsUp as a security Filter Chain
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        //Configuring the filterChain we need through the HttpSecurity Object

        //Authentication
        http.httpBasic().and().formLogin();

        //Authorization
        //The below means that all the requests must be authenticated

        //The first part of the request is called the matcher method
        //the second part of the request is called the rule
        http.authorizeHttpRequests().anyRequest().authenticated();
        //CSRF & CORS Configs
        return http.build();
    }

  //we are using the default manager and provider for Http Basic but overriding the userDetails service and the password encoder
  //My httpBasic filter has been configured such that my userDetails service will retrun the below user
    @Bean
    public UserDetailsService userDetailsService(){

        //Every User In My Application must take this format so that they are a spring security user
        UserDetails u = User.builder()
                .username("caleb")
                .password(passwordEncoder().encode("password"))
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(u);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
