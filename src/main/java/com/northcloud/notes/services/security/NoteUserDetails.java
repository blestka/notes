package com.northcloud.notes.services.security;

import com.northcloud.notes.services.model.Note;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class NoteUserDetails implements UserDetails {
    private final Note note;

    public NoteUserDetails(Note note) {
        this.note = note;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return Optional.ofNullable(note.getPassword()).orElse("");
    }

    @Override
    public String getUsername() {
        return note.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return note.getExpiredAt().isAfter(LocalDateTime.now(Clock.systemUTC()));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
