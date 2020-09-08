package com.ssgdfcrm.todolist.mail.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDto {

    private String address;
    private String title;
    private String message;

}
