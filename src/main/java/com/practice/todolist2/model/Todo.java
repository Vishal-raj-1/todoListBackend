package com.practice.todolist2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Todo")
public class Todo {
    @Id
    private String id;
    private String task;
    private boolean isCompleted;

    public Todo(String id, String task, boolean isCompleted) {
        this.id = id;
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public Todo(){

    }
}
