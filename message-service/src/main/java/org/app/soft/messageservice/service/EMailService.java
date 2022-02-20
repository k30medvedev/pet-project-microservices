package org.app.soft.messageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.app.soft.messageservice.domain.MailDataDto;
import org.app.soft.messageservice.domain.MailDto;
import org.app.soft.messageservice.domain.MailData;
import org.app.soft.messageservice.model.Mail;
import org.app.soft.messageservice.repository.MailRepository;
import org.app.soft.messageservice.service.exceptions.MailRequestCannotBeNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EMailService {

    private final MailRepository mailRepository;
    private final EMailMapper mailMapper;

    @Autowired
    private EMailSender eMailSender;

    @Value("${app.mail.name}")
    String mailFrom;

    Logger logger = LoggerFactory.getLogger(EMailService.class);

    public List<MailDto> findAll() {
        List<Mail> mailList = mailRepository.findAll();
        logger.debug("findAll() in EmailService");
        return mailMapper.mailDtoList(mailList);
    }

    public MailDto findById(String id) {
        Mail existMail = mailRepository.findByUUID(id);
        logger.debug("findById() in EmailService");
        return mailMapper.mailToMailDto(existMail);
    }

    public void deleteById(String uuid) {
        mailRepository.deleteByUUID(uuid);
        logger.debug("mail with " + uuid + " deleted");
    }

    public Mail saveInDataBase(MailData mailRequest, String htmlMsg) {
        if (mailRequest != null) {
            Mail mail = new Mail();
            mail.setMailFrom(mailFrom);
            mail.setMailTo(mailRequest.getEmail());
            mail.setTemplate(mailRequest.getTemplate());
            mail.setBody(htmlMsg);
            mail.setStatus(mailRequest.getStatus());
            mailRepository.save(mail);
            logger.debug(mail + "sent and save in database");
            return mail;
        } else throw new MailRequestCannotBeNullException();
    }

    public void updateStatusMailAndSave(Mail mail) {
        mailRepository.save(mail);
        logger.debug(mail.getStatus() + "status set and saved in database");
    }

    public void send(MailDataDto mailRequest) throws MessagingException, JsonProcessingException {
        eMailSender.sendEmail(mailRequest);
        logger.debug("mailService.send mailRequest => mailsSender ");
    }
}

