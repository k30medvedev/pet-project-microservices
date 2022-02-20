package org.app.soft.messageservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "template", nullable = false, unique = true)
    private String templateName;

    @Column(name = "html", nullable = true)
    private String content;

}
