package org.app.soft.messageservice.service;

import org.app.soft.messageservice.domain.MailData;
import org.app.soft.messageservice.domain.MailDto;
import org.app.soft.messageservice.model.Mail;
import org.app.soft.messageservice.model.Status;
import org.app.soft.messageservice.repository.MailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class EMailServiceTest {

    private MailRepository mailRepository;
    private EMailMapper mailMapper;
    private EMailService eMailService;

    @BeforeEach
    void setUp() {
        mailRepository = mock(MailRepository.class);
        mailMapper = mock(EMailMapper.class);
        eMailService = new EMailService(mailRepository, mailMapper);
    }

    @Test
    void shouldFindAllMailsTest() {
        List<Mail> mailList = new ArrayList<>();
        List<MailDto> listDto = new ArrayList<>();

        when(mailRepository.findAll()).thenReturn(mailList);
        when(mailMapper.mailDtoList(mailList)).thenReturn(listDto);

        // WHEN
        List<MailDto> listExpected = eMailService.findAll();
        List<MailDto> listActual = mailMapper.mailDtoList(mailRepository.findAll());

        // THEN
        verify(mailRepository, times(2)).findAll();
        verifyNoMoreInteractions(mailRepository);

        assertEquals(listExpected, listActual);
    }

    @Test
    void shouldFindByIdTest() {
        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        Mail actualMail = new Mail();
        MailDto mailDto = new MailDto();

        when(mailRepository.findByUUID(id)).thenReturn(actualMail);
        when(mailMapper.mailToMailDto(actualMail)).thenReturn(mailDto);

        // WHEN
        MailDto expected = eMailService.findById(id);
        MailDto actual = mailMapper.mailToMailDto(mailRepository.findByUUID(id));

        // THEN
        verify(mailRepository, times(2)).findByUUID(id);
        verifyNoMoreInteractions(mailRepository);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteByIdTest() {
        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        doNothing().when(mailRepository)
                .deleteByUUID(id);

        // WHEN
        eMailService.deleteById(id);

        // THEN
        verify(mailRepository, only()).deleteByUUID(id);
    }

    @Test
    void shouldSaveInDataBaseTest() {
        //GIVEN
        String html = "html";
        MailData mailData = new MailData();
        mailData.setEmail("test@gmail.com");
        mailData.setTemplate(Template.FORGET_PASSWORD);
        mailData.setStatus(Status.NEW);

        Mail actualMail = new Mail();
        actualMail.setMailTo(mailData.getEmail());
        actualMail.setTemplate(mailData.getTemplate());
        actualMail.setBody(html);
        actualMail.setStatus(mailData.getStatus());

        when(mailRepository.save(actualMail)).thenReturn(actualMail);

        Mail expected = eMailService.saveInDataBase(mailData, html);

        assertNotNull(actualMail);
        assertEquals(expected, actualMail);
        verify(mailRepository, times(1)).save(actualMail);
    }

    @Test
    void shouldSetStatusAndSaveInDataBaseTest() {
        //GIVEN
        Mail mail = new Mail();
        mail.setStatus(Status.SENT);

        when(mailRepository.save(mail)).thenReturn(mail);

        //WHEN
        eMailService.updateStatusMailAndSave(mail);

        //THEN
        verify(mailRepository, only()).save(mail);

    }
}