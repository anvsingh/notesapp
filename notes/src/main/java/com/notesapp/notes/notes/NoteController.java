package com.notesapp.notes.notes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class NoteController {
	
	@Autowired NoteService service;

    @GetMapping("{user}/note")
    public List<NoteHolder> getNotes() {
        return service.getNotes();
    }

    @PostMapping("{user}/note")
    public void postNotes(@RequestBody NoteHolderDTO dto, @PathVariable("user") Long user) {
        service.add(dto, user);
    }

    @GetMapping("/{user}/note/{note}")
    public NoteHolder getById(@PathVariable(required = true) long user, @PathVariable(required = true) long note) {
        return service.getNoteHolderById(user);
    }

    @DeleteMapping("/{id}/note")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}
