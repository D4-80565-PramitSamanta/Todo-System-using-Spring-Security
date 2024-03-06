package com.app.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.convert.JodaTimeConverters.DateToDateTimeConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.app.JWTTokenProvider;
import com.app.dao.RoleRepository;
import com.app.dao.UserRepository;
import com.app.dto.ApiResponse;
import com.app.dto.JwtAuthResponse;
import com.app.dto.LoginDTO;
import com.app.dto.RegDTO;
import com.app.entities.Role;
import com.app.entities.User;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository udao;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository rdao;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	AuthenticationManager authManager;
	// another cool thing about security
	
	@Autowired
    JWTTokenProvider jwtTokenProvider;

	
	@Override
	@Transactional
	public ApiResponse register(RegDTO drdto) {
		if(udao.existsByUsername(drdto.getUsername()))
		{
			throw new ResourceAccessException("username exists");
		}else if(udao.existsByEmail(drdto.getEmail()))
		{
			throw new ResourceAccessException("email exists");
		}
		
		User user = new User();
		user.setEmail(drdto.getEmail());
		user.setName(drdto.getName());
		user.setPassword(encoder.encode(drdto.getPassword()));
		System.out.println(drdto.getUsername());
		user.setUsername(drdto.getUsername());
		Set<Role> roles = new HashSet<Role>();
		Role r = rdao.findByName("ROLE_USER");
		roles.add(r);
		user.setRoles(roles);
		udao.save(user);
		return (new ApiResponse(201,"user added successfully!!"));
	}
	
	@Override
    public JwtAuthResponse login(LoginDTO loginDto) {

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameoremail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = udao.findByUsernameOrEmail(loginDto.getUsernameoremail(), loginDto.getUsernameoremail());
        String role = null;
        if(userOptional.isPresent())
        {
        	User loggedinUser = userOptional.get();
        	Optional<Role> optionalRole = loggedinUser.getRoles().stream().findFirst();
        	if(optionalRole.isPresent())
        	{
        		Role userRole = optionalRole.get();
        		role = userRole.getName();
        	}
        }
        
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
        
    }

}
