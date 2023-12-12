package com.practice.todolist2.utils;

import com.practice.todolist2.dto.TodoDTO;
import com.practice.todolist2.model.Todo;
import org.springframework.beans.BeanUtils;

public class EntityDTOConverter {

    public static TodoDTO entityToDto(Todo todo){
        TodoDTO todoDTO = new TodoDTO();
        BeanUtils.copyProperties(todo, todoDTO);
        return todoDTO;
    }

    public static Todo dtoToEntity(TodoDTO todoDTO){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoDTO, todo);
        return todo;
    }
}
