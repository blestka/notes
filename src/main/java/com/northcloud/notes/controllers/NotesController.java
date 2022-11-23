package com.northcloud.notes.controllers;

import com.northcloud.notes.controllers.model.CreateNoteRequest;
import com.northcloud.notes.controllers.model.CreateNoteResponse;
import com.northcloud.notes.exceptions.NotesException;
import com.northcloud.notes.services.NotesService;
import com.northcloud.notes.services.model.Note;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @PostMapping
    public CreateNoteResponse saveNote(@Valid @RequestBody CreateNoteRequest createNoteRequest) {
        return notesService.createNote(createNoteRequest);
    }

    @GetMapping("{id}")
    public String getNote(@PathVariable String id) throws NotesException {
        Note note = notesService.findNonExpiredById(UUID.fromString(id));
        return note.getText();
    }

    @ExceptionHandler(NotesException.class)
    public ResponseEntity<Map> processException(NotesException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(Map.of("message", e.getMessage()));
    }

}
