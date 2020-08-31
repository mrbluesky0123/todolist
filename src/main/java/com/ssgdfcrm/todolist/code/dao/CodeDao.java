package com.ssgdfcrm.todolist.code.dao;

import com.ssgdfcrm.todolist.code.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeDao extends JpaRepository<Code, Long> {

    List<Code> findByCdGrp(String cdGrp);
    List<Code> findByCdKey(String cdKey);

}
