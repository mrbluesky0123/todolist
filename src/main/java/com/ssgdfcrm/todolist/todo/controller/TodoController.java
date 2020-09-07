package com.ssgdfcrm.todolist.todo.controller;

import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.code.service.CodeService;
import com.ssgdfcrm.todolist.person.model.Person;
import com.ssgdfcrm.todolist.person.service.PersonService;
import com.ssgdfcrm.todolist.todo.dto.TodoRequest;
import com.ssgdfcrm.todolist.todo.model.Todo;
import com.ssgdfcrm.todolist.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<Todo> todoList = this.todoService.getTodoList("ALL", "ALL", "ALL");
        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");
        List<Code> statusCodeList = this.codeService.getCodeByCdGrp("PGM_STS");
        List<Person> personList = this.personService.getAllPersonList();

//        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");

        modelAndView.addObject("todoList", todoList);
        modelAndView.addObject("partCodeList", partCodeList);
        modelAndView.addObject("personList", personList);
        modelAndView.addObject("statusCodeList", statusCodeList);
        modelAndView.setViewName("alltodolist");

        return modelAndView;
    }

    @GetMapping("/refreshtodolist")
    public ModelAndView refreshAllTodoList(@RequestParam String status,
                                           @RequestParam String partName,
                                           @RequestParam String developerId) {

        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = this.todoService.getTodoList(status, partName, developerId);

        modelAndView.setViewName("tablefragment");
        modelAndView.addObject("todoList", todoList);
        return modelAndView;
    }

    @GetMapping("/singletodo/{id}")
    public String redirectSingleTodo(@PathVariable int id) {
        log.error(("##########$$$$$$$$$$$$$$$###############"));
        return "redirect:/todoview/" + id;
    }

    @GetMapping("/todoview/{id}")
    public ModelAndView getSingleTodo(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        Todo todo = this.todoService.getTodoByTodoId(id);
        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");
        List<Code> statusCodeList = this.codeService.getCodeByCdGrp("PGM_STS");
        List<Person> personList = this.personService.getAllPersonList();

        modelAndView.setViewName("singletodolist");
        modelAndView.addObject("todo", todo);
        modelAndView.addObject("partCodeList", partCodeList);
        modelAndView.addObject("personList", personList);
        modelAndView.addObject("statusCodeList", statusCodeList);
        return modelAndView;

    }

    @PostMapping("/singletodo")
    public int saveSingleTodo(@RequestBody TodoRequest todoRequest){
        int result = 0;
        log.debug(todoRequest.toString());
        Todo newTodo = this.todoService.saveTodo(todoRequest.getTodo());
        if(newTodo == null) {
            result = -1;
        }
        return result;
    }

}


