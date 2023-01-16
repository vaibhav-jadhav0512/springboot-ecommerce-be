package com.ecommerce.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class EcommerceBackendApplication {

    public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
    }
}