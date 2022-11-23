package com.northcloud.notes.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotesException extends Exception {
    private HttpStatus httpStatus;

    public NotesException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
