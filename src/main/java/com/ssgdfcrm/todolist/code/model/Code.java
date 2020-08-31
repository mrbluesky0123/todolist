package com.ssgdfcrm.todolist.code.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name="CODE")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="cd_grp")
    private String cdGrp;

    @Column(name="cd_key")
    private String cdKey;

    @Column(name="cd_val")
    private String cdVal;

}
