package com.practice.todolist2.controller;

import com.practice.todolist2.dto.TodoDTO;
import com.practice.todolist2.service.TodoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        List<TodoDTO> allTodos = todoService.getAllTodos();
        return ResponseEntity.ok(allTodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable String id){
        try {
            TodoDTO todoDTO =  todoService.getTodoById(id);
            return ResponseEntity.ok(todoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<TodoDTO> postTodo(@RequestBody @Valid TodoDTO todoDTO){
        TodoDTO savedTodo = todoService.postTodo(todoDTO);
        return ResponseEntity.ok(savedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id){
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
