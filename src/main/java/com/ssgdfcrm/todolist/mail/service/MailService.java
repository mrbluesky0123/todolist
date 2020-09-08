package com.ssgdfcrm.todolist.mail.service;

import com.ssgdfcrm.todolist.person.dao.PersonDao;
import com.ssgdfcrm.todolist.person.model.Person;
import com.ssgdfcrm.todolist.todo.model.Todo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;

@Service
@NoArgsConstructor
@Slf4j
public class MailService {

    private PersonDao personDao;
    private JavaMailSender mailSender;

    @Autowired
    public MailService(PersonDao personDao, JavaMailSender mailSender) {
        this.personDao = personDao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(Todo todo, String changeKind, String personName) {

        String to = this.getMailAddress(personName);
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String mailContents = this.getMailContents(todo, changeKind);

        try {

            helper.setTo(to);
            helper.setFrom("donotreply@gmail.com");
            if("C".equals(changeKind)) {
                helper.setSubject("[Todo알림] 새로운 Todo - " + todo.getPgmHnm() + "(" + todo.getPgmId() + ")");
            } else if("U".equals(changeKind)) {
                helper.setSubject("[Todo알림] 변경된 Todo - " + todo.getPgmHnm() + "(" + todo.getPgmId() + ")");
            }
            helper.setText(mailContents, true);

            log.info("##### Mailto: {}", to);
            log.info("##### Contents: {}", mailContents);

            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.error(e.getMessage());
            log.error("Making message failed.");
        } catch (MailException me) {
            log.error(me.getMessage());
            log.error("Sending mail failed.");
        }

        log.info("Sending mail complete.");

    }

    public String getMailAddress(String personName) {

        Person targetPerson = this.personDao.findByPersonNm(personName);
        String mailAddress;
        if("깡승보".equals(personName)) {
            mailAddress = "gogetit88@gmail.com";
        } else {
            mailAddress = "q" + targetPerson.getPersonId() + "@shinsegae.com";
        }
        return mailAddress;

    }

    public String getMailContents(Todo todo, String changeKind) {

        String regrName = todo.getRegrId();
        String regDate = todo.getRegDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String programId = todo.getPgmId();
        String programNm = todo.getPgmNm();
        String programHnm = todo.getPgmHnm();
        String dvlprName = todo.getDvlprId();
        String link = "http://198.13.47.188:8080/todoview/" + todo.getId();

        String contents = "";
        if("C".equals(changeKind)) {
            contents += "<p>새로운 todo가 등록되었습니다. 아래 내용을 확인해주세요.<p>";
        } else if("U".equals(changeKind)) {
            contents += "<p>귀하의 todo가 변경되었습니다. 아래 내용을 확인해주세요.<p>";
        }
        contents += "<p>";
        contents += "* 작성자: " + regrName + "<br/>";
        contents += "* 작성일자: " + regDate + "<br/>";
        contents += "* 담당자: " + dvlprName + "<br/>";
        contents += "* 프로그램ID: " + programId + "<br/>";
        contents += "* 프로그램명: " + programNm + "<br/>";
        contents += "* 프로그램한글명: " + programHnm + "<br/>";
        contents += "* 링크: <a href=\"" + link + "\">" + "바로가기" + "</a><br/>";
        contents += "</p>";
        contents += "<p>" + "(Do not reply to this mail.)" + "</p>";

        return contents;

    }

}
