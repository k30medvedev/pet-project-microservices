package org.app.soft.messageservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.soft.messageservice.domain.MailDto;
import org.app.soft.messageservice.model.Mail;
import org.app.soft.messageservice.service.EMailSender;
import org.app.soft.messageservice.service.EMailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class MailControllerTest {

    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EMailService emailService;
    @MockBean
    private EMailSender eMailSender;


    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }


    @Test
    void shouldGetAllMails() throws Exception {

        mockMvc.perform(get("/mails"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void findMailById() throws Exception {
        //GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        MailDto mailDto = new MailDto();
        mailDto.setId(id);
        mailDto.setBody("test");

        //WHEN
        Mockito.when(emailService.findById(id)).thenReturn(mailDto);

        //THEN
        mockMvc.perform(get("/mails/" + id))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.body").value("test"));
    }

    @Test
    void deleteMailById() throws Exception {
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        Mail mail = new Mail();
        mail.setId(id);

        // THEN
        mockMvc.perform(delete("/mails/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void sendEmail() throws Exception {
//        MailDataDto mailDataDto = new MailDataDto();
//        mailDataDto.setEmail("1Test@test");
//        mailDataDto.setSubject("Subject");
//        mailDataDto.setTemplate(Template.FORGET_PASSWORD);
//
//        String orderRequestToJson = mapper.writeValueAsString(mailDataDto);
//
//        Mockito.when(emailService.send(mailDataDto))
//
//        // THEN
//        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
//                .content(orderRequestToJson)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(201));

    }
}