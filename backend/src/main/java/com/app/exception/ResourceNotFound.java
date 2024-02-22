package com.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResourceNotFound extends RuntimeException{
    private String message;
    public ResourceNotFound(String message){
        this.message = message;
        }
}
