package com.ssgdfcrm.todolist.todo.dao;

import com.ssgdfcrm.todolist.todo.model.Todo;

import java.util.List;

public interface TodoDaoCustom {

    List<Todo> findByConditions(String status, String partName, String personId);

}
