package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {
	
	@Bean
	public static PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authorize) -> { // Configure authorization rules
	            authorize
	                .antMatchers(HttpMethod.POST, "/todos/**").hasRole("ADMIN") // Allow POST requests to "/todos/**" only for users with role "ADMIN"
	                .antMatchers(HttpMethod.PUT,"/todos/**").hasRole("ADMIN")
	                .antMatchers(HttpMethod.DELETE,"/todos/**").hasRole("ADMIN")
	                .antMatchers(HttpMethod.GET,"/todos/**").hasAnyRole("ADMIN","USER")
	                .anyRequest().authenticated(); // Require authentication for any other request
	        })
	        .httpBasic(Customizer.withDefaults()) // Use HTTP Basic authentication with default settings
	        .csrf().disable(); // Disable CSRF protection (place this last)
	    return http.build(); // Build and return the configured HttpSecurity object
	}

	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails ramesh = User.builder()
				.username("ramesh")
				.password(encoder().encode("111"))
				.roles("USER")
				.build();
		
		
		UserDetails suresh = User.builder()
				.username("suresh")
				.password(encoder().encode("222"))
				.roles("ADMIN")
				.build();
		
		
		return new InMemoryUserDetailsManager(ramesh, suresh);
	}
}
