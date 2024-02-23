package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ApiResponse;
import com.app.dto.TodoDTO;
import com.app.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTodo(@RequestBody TodoDTO todoDTO) {
        ApiResponse response = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.getTodo(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
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
}
