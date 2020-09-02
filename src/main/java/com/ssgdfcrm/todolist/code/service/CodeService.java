package com.ssgdfcrm.todolist.code.service;

import com.ssgdfcrm.todolist.code.dao.CodeDao;
import com.ssgdfcrm.todolist.code.model.Code;
import com.ssgdfcrm.todolist.todo.dao.TodoDao;
import com.ssgdfcrm.todolist.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    private CodeDao codeDao;

    @Autowired
    public CodeService(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    public List<Code> getCodeByCdGrp(String codeGrp) {
        return this.codeDao.findByCdGrp(codeGrp);
    }

}
