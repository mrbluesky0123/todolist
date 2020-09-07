package com.ssgdfcrm.todolist.todo.dto;

import com.ssgdfcrm.todolist.todo.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private Todo todo;
    private String mailSendYn;
}
