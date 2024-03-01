package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.RegDTO;

public interface AuthService {
	public ApiResponse register(RegDTO drdto );
}
