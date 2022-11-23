package com.northcloud.notes.services;

import com.northcloud.notes.controllers.model.CreateNoteRequest;
import com.northcloud.notes.controllers.model.CreateNoteResponse;
import com.northcloud.notes.dao.NotesRepository;
import com.northcloud.notes.dao.model.NoteEntity;
import com.northcloud.notes.exceptions.NotesException;
import com.northcloud.notes.services.mappers.NotesModelsMapper;
import com.northcloud.notes.services.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final NotesRepository notesRepository;
    private final NotesModelsMapper notesModelsMapper;
    private final PasswordEncoder passwordEncoder;

    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        NoteEntity noteEntity = notesModelsMapper.toDaoModel(createNoteRequest);
        noteEntity.setId(UUID.randomUUID());
        if(createNoteRequest.getPassword() != null && !createNoteRequest.getPassword().isEmpty()) {
            noteEntity.setPassword(passwordEncoder.encode(createNoteRequest.getPassword()));
        }
        NoteEntity createdEntity = notesRepository.save(noteEntity);
        return notesModelsMapper.toCreateResponse(createdEntity);
    }

    public Note findNonExpiredById(UUID id) throws NotesException {
        Optional<NoteEntity> noteEntity = notesRepository.findByIdAndExpiredAtAfter(id, LocalDateTime.now());
        return noteEntity.map(notesModelsMapper::toServiceModel).orElseThrow(() -> new NotesException("Note does not exists or has been expired", HttpStatus.BAD_REQUEST));
    }

    public Note findById(UUID id) throws NotesException {
        Optional<NoteEntity> noteEntity = notesRepository.findById(id);
        return noteEntity.map(notesModelsMapper::toServiceModel).orElseThrow(() -> new NotesException("Note does not exists or has been expired", HttpStatus.BAD_REQUEST));
    }

}
