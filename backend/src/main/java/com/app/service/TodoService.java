package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.TodoDTO;

public interface TodoService {
	ApiResponse addTodo(TodoDTO dto);
	TodoDTO getTodo(Long id);
    List<TodoDTO> getAllTodos();
    ApiResponse updateTodo(TodoDTO todoDto, Long id);
    ApiResponse completeTodo(Long id);
    ApiResponse inCompleteTodo(Long id);
	ApiResponse editTodo(long id, TodoDTO dto);
}	
