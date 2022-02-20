package org.app.soft.messageservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.app.soft.messageservice.domain.MailData;
import org.app.soft.messageservice.domain.MailDataDto;
import org.app.soft.messageservice.domain.MailDto;
import org.app.soft.messageservice.model.Mail;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EMailMapper {
    Logger logger = LoggerFactory.getLogger(EMailMapper.class);

    private final ModelMapper modelMapper;



    public List<MailDto> mailDtoList(List<Mail> mailList) {
        Type listType = new TypeToken<List<MailDto>>() {
        }.getType();
        logger.debug("convert List<Mail> to List<MailDto>" + mailList);
        return modelMapper.map(mailList, listType);
    }

    public MailDto mailToMailDto(Mail mail) {
        logger.debug("convert mail to mailDto" + mail);
        return modelMapper.map(mail, MailDto.class);
    }

    public MailData mailDataDtoToMailData(MailDataDto mailDataDto) {
        logger.debug("convert mailData to mailDataDto" + mailDataDto);
        return modelMapper.map(mailDataDto, MailData.class);
    }
}
