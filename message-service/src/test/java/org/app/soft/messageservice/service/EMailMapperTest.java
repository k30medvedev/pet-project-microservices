package org.app.soft.messageservice.service;

import org.app.soft.messageservice.domain.MailDto;
import org.app.soft.messageservice.model.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EMailMapperTest {

    private ModelMapper modelMapper;
    private EMailMapper eMailMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        eMailMapper = new EMailMapper(modelMapper);
    }

    @Test
    void mailDtoList() {
        //GIVEN
        List<Mail> mailList = new ArrayList<>();
        List<MailDto> mailListDtoActual = new ArrayList<>();
        Type listType = new TypeToken<List<MailDto>>() {
        }.getType();

        when(modelMapper.map(mailList, listType)).thenReturn(mailListDtoActual);
        //WHEN

        List<MailDto> expected = modelMapper.map(mailList, listType);

        //THEN
        assertEquals(expected, mailListDtoActual);
    }

    @Test
    void mailToMailDto() {
    }

    @Test
    void mailDataDtoToMailData() {
    }
}