package com.ssgdfcrm.todolist.person.dao;

import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {

    List<Person> findAllByOrderByPersonNm();
    List<Person> findByPersonId(String pId);
    Person findByPersonNm(String pNm);

}
