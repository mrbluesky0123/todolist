package com.ssgdfcrm.todolist.todo.dao;

import com.querydsl.core.BooleanBuilder;
import com.ssgdfcrm.todolist.todo.model.QTodo;
import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TodoDaoCustomImpl extends QuerydslRepositorySupport implements TodoDaoCustom{
    public TodoDaoCustomImpl() {
        super(Todo.class);
    }

    @Override
    public List<Todo> findByConditions(String status, String partName, String personId) {
        final QTodo todo = QTodo.todo;
        BooleanBuilder builder = new BooleanBuilder();
        if(!status.equals("ALL")) {

        }
        return from(todo)

    }
}
