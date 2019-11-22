package com.notesapp.notes.notes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteService {

	@Autowired
	NoteRepository noteRepo;

	public void add(NoteHolderDTO dto, Long user) {
		noteRepo.save(toEntity(dto, user));
	}

	public void delete(long id) {
		noteRepo.deleteById(id);
	}

	public List<NoteHolder> getNotes() {
		return (List<NoteHolder>) noteRepo.findAll();
	}

	public NoteHolder getNoteHolderById(long id) {
        Optional<NoteHolder> optionalUser = noteRepo.findById(id);
        return optionalUser.get();
    }

	private NoteHolder toEntity(NoteHolderDTO dto, Long user) {
		NoteHolder entity = new NoteHolder();
		entity.setNote(dto.getNote());
		entity.setTitle(dto.getTitle());
		entity.setUser(user);
		return entity;
	}



}
