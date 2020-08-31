package com.ssgdfcrm.todolist.todo.dao;

import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDao {

    List<Todo> findByRegrId(String regrId);
    List<Todo> findByPgmPart(String pgmPart);
    Todo save(Todo todo);


}
