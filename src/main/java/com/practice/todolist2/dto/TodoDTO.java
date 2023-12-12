package com.practice.todolist2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class TodoDTO {
    private String id;
    @NotBlank(message = "task can't be blank")
    private String task;
    @NotNull(message = "mention task status")
    private boolean isCompleted;

    public TodoDTO(String id, String task, boolean isCompleted) {
        this.id = id;
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public TodoDTO(){

    }
}
