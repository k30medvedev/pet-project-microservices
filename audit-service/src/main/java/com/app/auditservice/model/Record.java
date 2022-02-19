package com.app.auditservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", nullable = false)
    private String id;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "body")
    private String body;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_action")
    private EntityAction entityAction;

    @Column(name = "entity_type")
    private String entityType;

}
