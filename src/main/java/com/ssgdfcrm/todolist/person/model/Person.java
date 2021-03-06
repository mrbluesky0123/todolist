package com.ssgdfcrm.todolist.person.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name="PERSON")
public class Person {

    @Id
    @Column(name="p_id")
    private String personId;

    @Column(name="p_nm")
    private String personNm;

    @Column(name="prt_nm")
    private String prtNm;

}
