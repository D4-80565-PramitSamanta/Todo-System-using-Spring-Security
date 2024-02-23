package com.app;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.dao.UserDAO;
import com.app.entities.User;
import com.app.exception.ResourceNotFound;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO uDao;
	
	@Override
	public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
		 User user = uDao.findByUsernameOrEmail(usernameoremail, usernameoremail)
				.orElseThrow(()->new ResourceNotFound("not found"));
		 
		 Set<SimpleGrantedAuthority> authorites = user.getRoles().stream()
		 .map((role)->new SimpleGrantedAuthority(role.getName()))
		 .collect(Collectors.toSet());
		 
		 return new org.springframework.security.core.userdetails.User(
				 usernameoremail,
				 user.getPassword(),
				 authorites
				 );
				 
	}

}
