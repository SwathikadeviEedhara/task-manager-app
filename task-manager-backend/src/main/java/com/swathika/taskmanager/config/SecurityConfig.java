package com.swathika.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



	@Configuration
	@EnableMethodSecurity(prePostEnabled = true)
	public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/task/**").hasAnyRole("USER", "ADMIN")
	            .requestMatchers(HttpMethod.DELETE, "/api/task/**").hasRole("ADMIN")
	            .anyRequest().authenticated())
	        .httpBasic();
	    return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return NoOpPasswordEncoder.getInstance(); // for demo only
	}

	@Bean
	public UserDetailsService userDetailsService() {
	    UserDetails admin = User.withUsername("admin")
	        .password("adminpass")   
	        .roles("ADMIN")
	        .build();

	    UserDetails user = User.withUsername("user")
	        .password("password")    
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(admin, user);
	}

	}


