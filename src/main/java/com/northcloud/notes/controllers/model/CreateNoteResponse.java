package com.northcloud.notes.controllers.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateNoteResponse {
    private UUID id;
    private String text;
}
