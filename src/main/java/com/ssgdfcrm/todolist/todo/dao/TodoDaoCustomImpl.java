package com.ssgdfcrm.todolist.todo.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgdfcrm.todolist.todo.model.QTodo;
import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TodoDaoCustomImpl extends QuerydslRepositorySupport implements TodoDaoCustom{

    public TodoDaoCustomImpl() {
        super(Todo.class);
    }

    @Override
    public List<Todo> findByConditions(String status, String partName, String personId) {

        final QTodo todo = QTodo.todo;
        BooleanBuilder whereBuilder = new BooleanBuilder();

        // Search by status
        if(isStatusSpecificPartNameAllPersonIdAll(status, partName, personId)) {
            whereBuilder.and(todo.pgmSts.eq(status));

        // Search by part
        } else if(isStatusAllPartNameSpecificPersonIdAll(status, partName, personId)) {
            whereBuilder.and(todo.pgmPart.eq(partName));

        // Search by developer
        } else if(isStatusAllPartNameAllPersonIdSpecific(status, partName, personId)) {
            whereBuilder.and(todo.dvlprId.eq(personId));

        // Serach by part and developer
        } else if(isStatusAllPartNameSpecificPersonIdSpecific(status, partName, personId)) {
            whereBuilder.and(todo.pgmPart.eq(partName))
                    .and(todo.dvlprId.eq(personId));

        // Search by part and status
        } else if(isStatusSpecificPartNameSpecificPersonIdAll(status, partName, personId)) {
            whereBuilder.and(todo.pgmSts.eq(status))
                    .and(todo.pgmPart.eq(partName));

        // Search by status and developer
        } else if(isStatusSpecificPartNameAllPersonIdSpecific(status, partName, personId)) {
            whereBuilder.and(todo.pgmSts.eq(status))
                    .and(todo.dvlprId.eq(personId));

        // Search by all attribute
        } else if(isStatusSpecificPartNameSpecificPersonIdSpecific(status, partName, personId)) {
            whereBuilder.and(todo.pgmSts.eq(status))
                    .and(todo.pgmPart.eq(partName))
                    .and(todo.dvlprId.eq(personId));
        }
        return from(todo).where(whereBuilder).fetch();

    }

    public boolean isStatusAllPartNameAllPersonIdSpecific(String status, String partName, String personId) {
        return status.equals("ALL") && partName.equals("ALL") && !personId.equals("ALL");
    }

    public boolean isStatusAllPartNameSpecificPersonIdSpecific(String status, String partName, String personId) {
        return status.equals("ALL") && !partName.equals("ALL") && !personId.equals("ALL");
    }

    public boolean isStatusAllPartNameAllPersonIdAll(String status, String partName, String personId) {
        return status.equals("ALL") && partName.equals("ALL") && personId.equals("ALL");
    }

    public boolean isStatusAllPartNameSpecificPersonIdAll(String status, String partName, String personId) {
        return status.equals("ALL") && !partName.equals("ALL") && personId.equals("ALL");
    }

    public boolean isStatusSpecificPartNameSpecificPersonIdSpecific(String status, String partName, String personId) {
        return !status.equals("ALL") && !partName.equals("ALL") && !personId.equals("ALL");
    }

    public boolean isStatusSpecificPartNameSpecificPersonIdAll(String status, String partName, String personId) {
        return !status.equals("ALL") && !partName.equals("ALL") && personId.equals("ALL");
    }

    public boolean isStatusSpecificPartNameAllPersonIdAll(String status, String partName, String personId) {
        return !status.equals("ALL") && partName.equals("ALL") && personId.equals("ALL");
    }

    public boolean isStatusSpecificPartNameAllPersonIdSpecific(String status, String partName, String personId) {
        return !status.equals("ALL") && partName.equals("ALL") && !personId.equals("ALL");
    }

}
