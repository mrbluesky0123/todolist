package com.ssgdfcrm.todolist.todo.service;

import com.ssgdfcrm.todolist.todo.dao.TodoDao;
import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoDao todoDao;

    @Autowired
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<Todo> getAllTodoList() {
        return this.todoDao.findAllByOrderById();
    }

    public List<Todo> getTodoListByPgmPart(String pgmPart) {
        return this.todoDao.findByPgmPart(pgmPart);
    }

    public List<Todo> getTodoListByDvlprID(String dvlprId) {
        return this.todoDao.findByDvlprId(dvlprId);
    }

}
