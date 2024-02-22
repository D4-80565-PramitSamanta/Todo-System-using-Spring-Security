package com.app.handler;

import com.app.dto.ApiResponse;
import com.app.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> handleResourceNotFound (ResourceNotFound e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleResourceNotFound (MethodArgumentNotValidException e)
    {
        List<FieldError> errors = e.getFieldErrors();
        Map<String, String > map = errors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleResourceNotFound (ConstraintViolationException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleResourceNotFound (RuntimeException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
    }
}
