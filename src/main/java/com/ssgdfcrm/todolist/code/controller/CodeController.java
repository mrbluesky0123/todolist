package com.ssgdfcrm.todolist.code.controller;

import com.ssgdfcrm.todolist.code.dao.CodeDao;
import com.ssgdfcrm.todolist.code.model.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
public class CodeController {

    private CodeDao codeDao;

    @Autowired
    public CodeController(CodeDao codeDao) {
        this.codeDao = codeDao;
        log.error("###### CodeController");
    }

    @GetMapping("/partname")
    public ModelAndView getPartName() {

        ModelAndView modelAndView = new ModelAndView();
        List<Code> goodsList = this.codeDao.findByCdGrp("PART_NM");

        modelAndView.setViewName("example");
        modelAndView.addObject("goodsList", goodsList);

        return modelAndView;

    }

    @GetMapping("/t")
    public String test2() {
        return "World";
    }

}
