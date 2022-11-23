package com.northcloud.notes.services.security;

import com.northcloud.notes.exceptions.NotesException;
import com.northcloud.notes.services.NotesService;
import com.northcloud.notes.services.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteUserDetailsService implements UserDetailsService {
    private final NotesService notesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Note note = null;
        try {
            note = notesService.findById(UUID.fromString(username));
        } catch (NotesException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new NoteUserDetails(note);
    }
}
