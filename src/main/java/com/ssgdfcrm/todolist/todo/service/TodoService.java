package com.ssgdfcrm.todolist.todo.service;

import com.ssgdfcrm.todolist.todo.dao.TodoDao;
import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoService {

    private TodoDao todoDao;

    @Autowired
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<Todo> getTodoList(String status, String partName, String developerId) {
        return this.todoDao.findByConditions(status, partName, developerId);
    }

    public List<Todo> getTodoListByPgmPart(String pgmPart) {
        return this.todoDao.findByPgmPart(pgmPart);
    }

    public List<Todo> getTodoListByDvlprID(String dvlprId) {
        return this.todoDao.findByDvlprId(dvlprId);
    }

    public Todo getTodoByTodoId(int todoId) {
        return this.todoDao.findById(todoId);
    }

    public Todo saveTodo(Todo todo){
        return this.todoDao.save(todo);
    }

}
