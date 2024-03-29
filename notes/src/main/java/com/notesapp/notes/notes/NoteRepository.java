package com.notesapp.notes.notes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<NoteHolder, Long> {
	
	public List<NoteHolder> findNoteHolderByUser(Long user);
	public NoteHolder findNoteHolderByIdAndUser(Long note, Long user);
	public void deleteNoteHolderByIdAndUser(long note, long user);
	
}