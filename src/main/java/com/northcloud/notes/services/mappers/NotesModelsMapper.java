package com.northcloud.notes.services.mappers;

import com.northcloud.notes.controllers.model.CreateNoteRequest;
import com.northcloud.notes.controllers.model.CreateNoteResponse;
import com.northcloud.notes.dao.model.NoteEntity;
import com.northcloud.notes.services.model.Note;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NotesModelsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    NoteEntity toDaoModel(CreateNoteRequest createNoteRequest);

    CreateNoteResponse toCreateResponse(NoteEntity noteEntity);

    Note toServiceModel(NoteEntity noteEntity);

}
