package com.ssgdfcrm.todolist.person.service;

import com.ssgdfcrm.todolist.person.dao.PersonDao;
import com.ssgdfcrm.todolist.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPersonList() {
        return this.personDao.findAll();
    }

}
