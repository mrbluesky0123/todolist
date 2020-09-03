package com.ssgdfcrm.todolist.todo.dao;

import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDao extends JpaRepository<Todo, Long>, TodoDaoCustom {

    List<Todo> findAllByOrderById();
    Todo findById(int id);
    List<Todo> findByDvlprId(String dvlprId);
    List<Todo> findByPgmPart(String pgmPart);
    Todo save(Todo todo);


}
