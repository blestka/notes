package com.northcloud.notes.controllers.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateNoteRequest {
    @NotNull
    private String text;
    private LocalDateTime expiredAt;
    private String password;
}
