package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ApiResponse;
import com.app.dto.TodoDTO;
import com.app.service.TodoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addTodo(@RequestBody TodoDTO todoDTO) {
        ApiResponse response = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.getTodo(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    
    // Build Complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<ApiResponse> completeTodo(@PathVariable("id") Long todoId){
        ApiResponse res = todoService.completeTodo(todoId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<ApiResponse> inCompleteTodo(@PathVariable("id") Long todoId){
        ApiResponse res = todoService.inCompleteTodo(todoId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
    @PutMapping("edit/{id}")
    public ResponseEntity<ApiResponse> editTodo(@PathVariable long id, @RequestBody TodoDTO dto)
    {
    	ApiResponse res = todoService.editTodo(id,dto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteTodo(@PathVariable long id)
	{
		ApiResponse res = todoService.delete(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

}
