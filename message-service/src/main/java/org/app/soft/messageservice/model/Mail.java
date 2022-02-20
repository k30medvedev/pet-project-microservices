package org.app.soft.messageservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.app.soft.messageservice.service.Template;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "mails")
public class Mail {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "email_from")
    private String mailFrom;

    @Column(name = "email_to")
    private String mailTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "template")
    private Template template;

    @Column(name = "body")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "error")
    private String error;

}
