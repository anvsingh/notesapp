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
	
	public void update(NoteHolderDTO dto, Long user, Long note) {
		NoteHolder entity = noteRepo.findById(note).get();
		if(entity!= null) {
			entity.setNote(dto.getNote());
			entity.setTitle(dto.getTitle());
			noteRepo.save(entity);
		}else {
			noteRepo.save(toEntity(dto, user));
		}
	}

	public void deleteNoteHolderByNoteAndUser(long note, long user) {
		noteRepo.deleteNoteHolderByNoteAndUser(note, user);
	}

	public List<NoteHolder> getNotes(Long user) {
		return (List<NoteHolder>) noteRepo.findNoteHolderByUser(user);
	}

	public NoteHolder getNoteHolderByUserAndNote(Long user, Long note) {
        NoteHolder noteHolder = noteRepo.findNoteHolderByIdAndUser(note, user);
        return noteHolder;
    }

	private NoteHolder toEntity(NoteHolderDTO dto, Long user) {
		NoteHolder entity = new NoteHolder();
		entity.setNote(dto.getNote());
		entity.setTitle(dto.getTitle());
		entity.setUser(user);
		return entity;
	}



}
