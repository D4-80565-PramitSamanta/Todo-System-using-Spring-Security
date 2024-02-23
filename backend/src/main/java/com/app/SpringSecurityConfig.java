package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception
	{
		http.csrf().disable()
		.authorizeHttpRequests((autherize)->
		{autherize.anyRequest().authenticated();
	}).httpBasic(Customizer.withDefaults());
		return http.build();
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
