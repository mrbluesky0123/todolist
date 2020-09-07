package com.ssgdfcrm.todolist.todo.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name="TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="pgm_sts")
    private String pgmSts;

    @Column(name="reg_dt")
    private LocalDate regDt;

    @Column(name="regr_id")
    private String regrId;

    @Column(name="pgm_part")
    private String pgmPart;

    @Column(name="pgm_id")
    private String pgmId;

    @Column(name="pgm_nm")
    private String pgmNm;

    @Column(name="pgm_hnm")
    private String pgmHnm;

    @Column(name="dvlpr_id")
    private String dvlprId;

    @Column(name="sprvsr_dscrptn", columnDefinition = "TEXT")
    private String sprvsrDscrptn;

    @Column(name="dvlpr_dscrptn", columnDefinition = "TEXT")
    private String dvlprDscrptn;

    @Column(name="actn_req_dy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actnReqDy;

    @Column(name="actn_psb_dy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actnPsbDy;

    @Column(name="actn_cmpl_dy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actnCmplDy;

}
