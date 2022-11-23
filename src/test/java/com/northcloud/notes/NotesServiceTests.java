package com.northcloud.notes;

import com.northcloud.notes.controllers.model.CreateNoteRequest;
import com.northcloud.notes.dao.NotesRepository;
import com.northcloud.notes.dao.model.NoteEntity;
import com.northcloud.notes.exceptions.NotesException;
import com.northcloud.notes.services.NotesService;
import com.northcloud.notes.services.mappers.NotesModelsMapper;
import com.northcloud.notes.services.model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTests {
    @Mock
    private NotesRepository notesRepository;
    @Mock
    private NotesModelsMapper notesModelsMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private NotesService notesService;

    @Test
    public void testCreateNote() {
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setText("test note");
        createNoteRequest.setExpiredAt(LocalDateTime.now().plusDays(1));
        createNoteRequest.setPassword("mysecurepassword");

        NoteEntity note = new NoteEntity();
        note.setText(createNoteRequest.getText());
        note.setExpiredAt(createNoteRequest.getExpiredAt());

        when(notesModelsMapper.toDaoModel(createNoteRequest)).thenReturn(note);
        when(passwordEncoder.encode(createNoteRequest.getPassword())).thenReturn("encodedpassword");
        when(notesRepository.save(any(NoteEntity.class))).thenReturn(note);

        notesService.createNote(createNoteRequest);

        verify(notesModelsMapper, times(1)).toDaoModel(createNoteRequest);
        verify(passwordEncoder, times(1)).encode(createNoteRequest.getPassword());
        verify(notesRepository, times(1)).save(any(NoteEntity.class));
    }

    @Test
    public void testGetNote_success() throws NotesException {
        UUID id = UUID.randomUUID();

        NoteEntity note = new NoteEntity();
        note.setText("test");
        note.setExpiredAt(null);
        note.setPassword("asdasdasdas");

        when(notesRepository.findByIdAndExpiredAtAfter(eq(id), any(LocalDateTime.class))).thenReturn(Optional.of(note));
        when(notesModelsMapper.toServiceModel(note)).thenReturn(new Note());

        notesService.findNonExpiredById(id);

        verify(notesModelsMapper, times(1)).toServiceModel(note);
        verify(notesRepository, times(1)).findByIdAndExpiredAtAfter(eq(id), any());
    }

    @Test
    public void testGetNote_notFound() throws NotesException {
        UUID id = UUID.randomUUID();

        NoteEntity note = new NoteEntity();
        note.setText("test");
        note.setExpiredAt(null);
        note.setPassword("asdasdasdas");

        when(notesRepository.findByIdAndExpiredAtAfter(eq(id), any(LocalDateTime.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotesException.class, () -> {
            notesService.findNonExpiredById(id);
        });

        String expectedMessage = "Note does not exists or has been expired";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(notesModelsMapper, times(0)).toServiceModel(any());
        verify(notesRepository, times(1)).findByIdAndExpiredAtAfter(eq(id), any());
    }


}
