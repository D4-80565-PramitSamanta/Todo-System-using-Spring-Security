package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class ApiResponse {
    private Integer status=200;
    private String message;
    private LocalDateTime timeNow;

    public ApiResponse(String message) {
        this.message = message;
        timeNow = LocalDateTime.now();
    }

    public ApiResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        timeNow = LocalDateTime.now();
    }
}
