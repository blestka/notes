package com.northcloud.notes.services.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Note {
    private UUID id;
    private String text;
    private LocalDateTime expiredAt;
    private String password;
}
