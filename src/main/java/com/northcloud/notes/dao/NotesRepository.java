package com.northcloud.notes.dao;

import com.northcloud.notes.dao.model.NoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface NotesRepository extends CrudRepository<NoteEntity, UUID> {
    public Optional<NoteEntity> findByIdAndExpiredAtAfter(UUID id, LocalDateTime now);
}
