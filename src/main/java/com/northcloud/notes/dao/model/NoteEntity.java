package com.northcloud.notes.dao.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "NOTE")
@Table(name = "notes")
@Data
@EntityListeners(AuditingEntityListener.class)
public class NoteEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "varchar(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "text", length = 256)
    private String text;
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
    private String password;
    @CreatedDate
    @Column(name = "created_date", columnDefinition = "datetime", updatable = false)
    private LocalDateTime createdDate;
}
