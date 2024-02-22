package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TodoDAO;
import com.app.dto.ApiResponse;
import com.app.dto.TodoDTO;
import com.app.entities.Todo;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO todoDAO;

    @Autowired
    private ModelMapper modelMapper;


    public ApiResponse addTodo(TodoDTO dto) {
        Todo todo = modelMapper.map(dto, Todo.class);
        todoDAO.save(todo);
        return (new ApiResponse(201,"Todo added successfully!!"));
    }


    public TodoDTO getTodo(Long id) {
        Todo todo = todoDAO.findById(id).orElse(null);
        return modelMapper.map(todo, TodoDTO.class);
    }

    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoDAO.findAll();
        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }
}
