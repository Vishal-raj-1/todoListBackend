package com.practice.todolist2.service;

import com.practice.todolist2.dto.TodoDTO;
import com.practice.todolist2.model.Todo;
import com.practice.todolist2.repository.TodoRepository;
import com.practice.todolist2.utils.EntityDTOConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void getBookById(){
        String todoId = "1";
        TodoDTO expectedTodo = new TodoDTO(todoId, "Task 1", false);
        when(todoRepository.findById(todoId))
                .thenReturn(Optional.of(EntityDTOConverter.dtoToEntity(expectedTodo)));

        TodoDTO result = todoService.getTodoById(todoId);

        assertEquals(expectedTodo, result);
    }

    @Test
    public void getBookById_notFound(){
        String todoId = "1";
        when(todoRepository.findById(todoId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            todoService.getTodoById(todoId);
        });
    }

    @Test
    public void getAllTodos(){
        List<Todo> todos = Arrays.asList(
                new Todo("1", "Task 1", false),
                new Todo("2", "Task 2", false),
                new Todo("3", "Task 3", false)
        );

        when(todoRepository.findAll()).thenReturn(todos);

        List<TodoDTO> result = todoService.getAllTodos();

        verify(todoRepository, times(1)).findAll();

        List<TodoDTO> expectedTodos = todos.stream().map(EntityDTOConverter::entityToDto).collect(Collectors.toList());

        assertEquals(expectedTodos, result);
    }

    @Test
    public void postTodo() {
        Todo todo = new Todo("1", "Task 1", false);
        when(todoRepository.save(todo)).thenReturn(todo);

        TodoDTO expectedTodo = EntityDTOConverter.entityToDto(todo);
        TodoDTO result = todoService.postTodo(expectedTodo);

        verify(todoRepository, times(1)).save(todo);
        assertEquals(expectedTodo, result);
    }

    @Test
    public void deleteTodoById(){
        String todoId = "1";
        TodoDTO expectedTodo = new TodoDTO(todoId, "Task 1", false);

        doNothing().when(todoRepository).deleteById(todoId);

        todoService.deleteTodoById(todoId);

        verify(todoRepository, times(1)).deleteById(todoId);
    }
}
