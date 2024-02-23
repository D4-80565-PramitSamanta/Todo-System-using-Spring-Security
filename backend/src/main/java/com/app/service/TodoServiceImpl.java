package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.app.dao.TodoDAO;
import com.app.dto.ApiResponse;
import com.app.dto.TodoDTO;
import com.app.entities.Todo;
import com.app.exception.ResourceNotFound;

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


	@Override
	public ApiResponse updateTodo(TodoDTO todoDto, Long id) {
		 Todo todo = todoDAO.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("Todo not found with id : " + id));
	         todo.setTitle(todoDto.getTitle());
	         todo.setDescription(todoDto.getDescription());
	         todo.setCompleted(todoDto.isCompleted());
	         todoDAO.save(todo);
	         return (new ApiResponse(201,"Todo updated successfully!!"));        
	}


	@Override
	public ApiResponse completeTodo(Long id) {
		Todo todo = todoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);
        todoDAO.save(todo);
        return (new ApiResponse(201,"Todo is complete!!"));        
	}


	@Override
	public ApiResponse inCompleteTodo(Long id) {
		Todo todo = todoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);
        todoDAO.save(todo);
        return (new ApiResponse(201,"Todo is not complete!!"));     
	}


	@Override
	public ApiResponse editTodo(long id, TodoDTO dto) {
		Todo todo = todoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Todo not found with id : " + id));
		todo.setCompleted(dto.isCompleted());
		todo.setDescription(dto.getDescription());
		todo.setTitle(dto.getTitle());
		todoDAO.save(todo);
        return (new ApiResponse(201,"Todo edited !!"));     
	}


	@Override
	public ApiResponse delete(long id) {
		Todo todo = todoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Todo not found with id : " + id));
		todoDAO.delete(todo);
        return (new ApiResponse(201,"Todo deleted !!"));     
	}


}
