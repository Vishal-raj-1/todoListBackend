package com.practice.todolist2.service;

import com.practice.todolist2.dto.TodoDTO;
import com.practice.todolist2.model.Todo;
import com.practice.todolist2.repository.TodoRepository;
import com.practice.todolist2.utils.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoDTO> getAllTodos() {
        return todoRepository
                .findAll()
                .stream()
                .map(EntityDTOConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public TodoDTO postTodo(TodoDTO todoDTO) {
        return EntityDTOConverter.entityToDto(todoRepository.save(EntityDTOConverter.dtoToEntity(todoDTO)));
    }

    public TodoDTO getTodoById(String id) {
        Optional<Todo> savedTodo = todoRepository.findById(id);

        if(savedTodo.isPresent()){
            return EntityDTOConverter.entityToDto(savedTodo.get());
        }
        else{
            throw new NoSuchElementException("Book Not found");
        }
    }

    public void deleteTodoById(String id) {
        try {
            todoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException("Todo with ID " + id + " not found");
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error deleting todo with ID " + id, ex);
        }
    }
}
