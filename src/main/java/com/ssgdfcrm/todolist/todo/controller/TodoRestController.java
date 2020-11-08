package com.ssgdfcrm.todolist.todo.controller;

import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.code.service.CodeService;
import com.ssgdfcrm.todolist.common.dto.ResponseDto;
import com.ssgdfcrm.todolist.mail.service.MailService;
import com.ssgdfcrm.todolist.person.model.Person;
import com.ssgdfcrm.todolist.person.service.PersonService;
import com.ssgdfcrm.todolist.todo.dto.TodoRequest;
import com.ssgdfcrm.todolist.todo.model.Todo;
import com.ssgdfcrm.todolist.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class TodoRestController {

    private TodoService todoService;
    private CodeService codeService;
    private PersonService personService;
    private MailService mailService;

    @Autowired
    public TodoRestController(TodoService todoService, CodeService codeService, PersonService personService,
                          MailService mailService) {
        this.todoService = todoService;
        this.codeService = codeService;
        this.personService = personService;
        this.mailService = mailService;
    }

    @GetMapping("/alltodolistnew")
//    public ResponseDto<List> getAllTodoList(@RequestParam String status,
    public List<Todo> getAllTodoList(@RequestParam String status,
                                     @RequestParam String partName,
                                     @RequestParam String developerId) {

        List<Todo> todoList = this.todoService.getTodoList(status, partName, developerId);
        log.info("########### " + todoList.get(1).toString());
        ResponseDto<List> response = new ResponseDto<>(todoList);
        return todoList;

    }

    @GetMapping("/allpartcode_new")
    public List<Code> getAllPartCodeList() {
        List<Code> partCodeList = this.codeService.getCodeByCdGrp("PART_NM");
        return partCodeList;
    }

    @GetMapping("/allstatuscode_new")
    public List<Code> getAllStatusCodeList() {
        List<Code> statusCodeList = this.codeService.getCodeByCdGrp("PGM_STS");
        return statusCodeList;
    }

    @GetMapping("/allpersonlist_new")
    public List<Person> getAllPersonList() {
        List<Person> personList = this.personService.getAllPersonList();
        return personList;
    }

    @GetMapping("/todo/{id}")
    public Todo getSingleTodo(@PathVariable int id) {
        log.error("##### {}", "FUCK");
        return this.todoService.getTodoByTodoId(id);
    }

    @PostMapping("/newtodo")
    @ResponseBody
    public int saveSingleTodo(@RequestBody TodoRequest todoRequest){

        int result = 0;
        log.info(todoRequest.toString());
        Todo todo = todoRequest.getTodo();
        String changeKind = todoRequest.getChangeKind();
        String mailToDvlpr = todoRequest.getMailToDvlpr();
        String mailToRegr = todoRequest.getMailToRegr();

        Todo newTodo = this.todoService.saveTodo(todoRequest.getTodo());
        if(newTodo == null) {
            result = -1;
        }
        if(!"nul".equals(mailToDvlpr)) {
            this.mailService.sendMail(todo, changeKind, mailToDvlpr);
        }
        if(!"nul".equals(mailToRegr)) {
            this.mailService.sendMail(todo, changeKind, mailToRegr);
        }
        return result;

    }

}
