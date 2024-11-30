package com.kartik.RentRead.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


/**
 *
 *
 * @Configuration: Marks this class as a configuration class to be processed by Spring.
 * @Bean: Tells Spring to treat this method as a bean definition, so Spring will instantiate BCryptPasswordEncoder and manage it in the application context.
 * passwordEncoder(): This method returns an instance of BCryptPasswordEncoder, which Spring will use whenever a PasswordEncoder bean is required.
 */