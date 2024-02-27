//package com.blog.application.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import com.blog.application.Security.CustomUserDetailService;
//import com.blog.application.Security.JWTAuthenticationEntryPoint;
//import com.blog.application.Security.JwtAuthenticatorFilter;
//
//
//
////@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
////	
//	@Autowired
//	private CustomUserDetailService customUserDetailService;
//	
//	@Autowired
//	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	
//	@Autowired
//	private JwtAuthenticatorFilter jwtAuthenticatorFilter;
//	
//	@SuppressWarnings("removal")
//	@Bean
//	public SecurityFilterChain securityFilterChain( HttpSecurity http) {
//		http.
//		csrf().disable()
//		.authorizeHttpRequests()
//		
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//		
//	}
//	
//
//}
