package com.ssgdfcrm.todolist.todo.controller;

import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.code.service.CodeService;
import com.ssgdfcrm.todolist.person.model.Person;
import com.ssgdfcrm.todolist.person.service.PersonService;
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
    private CodeService codeService;
    private PersonService personService;

    @Autowired
    public TodoController(TodoService todoService, CodeService codeService, PersonService personService) {
        this.todoService = todoService;
        this.codeService = codeService;
        this.personService = personService;
//        log.error("###### TodoController");
    }

    @GetMapping("/alltodolist")
    public ModelAndView getAllTodoList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = this.todoService.getAllTodoList();
        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");
        List<Code> statusCodeList = this.codeService.getCodeByCdGrp("PGM_STS");
        List<Person> personList = this.personService.getAllPersonList();

//        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");

        modelAndView.addObject("todoList", todoList);
        modelAndView.addObject("partCodeList", partCodeList);
        modelAndView.addObject("statusCodeList", statusCodeList);
        modelAndView.addObject("personList", personList);
        modelAndView.setViewName("alltodolist");

        return modelAndView;
    }

    @GetMapping("/refreshtodolist")
    public ModelAndView refreshAllTodoList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = this.todoService.getAllTodoList();

        modelAndView.setViewName("tablefragment");
        modelAndView.addObject("todoList", todoList);
        return modelAndView;
    }

}


