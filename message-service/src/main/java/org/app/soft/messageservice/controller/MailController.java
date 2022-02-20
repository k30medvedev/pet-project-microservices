package org.app.soft.messageservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.app.soft.messageservice.service.EMailSender;
import org.app.soft.messageservice.service.EMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.app.soft.messageservice.domain.MailDataDto;
import org.app.soft.messageservice.domain.MailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController @RequiredArgsConstructor public class MailController {

    private final EMailService emailService;
    private final EMailSender eMailSender;

    Logger logger = LoggerFactory.getLogger(MailController.class);

    @GetMapping("/mails") ResponseEntity<List<MailDto>> getAllMails() {
        logger.debug("getAllMails() from MailController was requested");
        return new ResponseEntity<>(emailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/mails/{id}") ResponseEntity<MailDto> findMailById(@PathVariable String id) {
        MailDto mailDto = emailService.findById(id);
        logger.debug("mail with: " + id + "was requested");
        return ResponseEntity.status(200).body(mailDto);
    }

    @DeleteMapping("/mails/{id}") ResponseEntity<String> deleteMailById(@PathVariable("id") String id) {
        emailService.deleteById(id);
        logger.debug("mail with: " + id + "was requested to delete");
        return ResponseEntity.ok("mail was deleted:" + id);
    }

    @PostMapping("/mails") public ResponseEntity<String> sendEmail(@RequestBody MailDataDto mailRequest)
            throws MessagingException, JsonProcessingException {
        emailService.send(mailRequest);
        logger.debug("new mail requested to create:" + mailRequest);
        return ResponseEntity.status(201).body("Email sent");
    }

}
