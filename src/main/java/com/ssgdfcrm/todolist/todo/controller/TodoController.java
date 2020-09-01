package com.ssgdfcrm.todolist.todo.controller;

import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.todo.model.Todo;
import com.ssgdfcrm.todolist.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
        log.error("###### TodoController");
    }

    @GetMapping("/alltodolist")
    public ModelAndView getAllTodoList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = this.todoService.getAllTodoList();

        modelAndView.setViewName("alltodolist");
        modelAndView.addObject("todoList", todoList);
        return modelAndView;
    }

}
