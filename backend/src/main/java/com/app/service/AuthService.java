package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.JwtAuthResponse;
import com.app.dto.LoginDTO;
import com.app.dto.RegDTO;

public interface AuthService {
	public ApiResponse register(RegDTO drdto );
    public JwtAuthResponse login(LoginDTO loginDto);
}
