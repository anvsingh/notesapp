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
@RequestMapping("user/{user}")
public class NoteController {

	@Autowired
	NoteService service;

	/**
	 * Get all the notes for a user
	 * 
	 * @return List of NoteHolder for a User
	 */
	@GetMapping("/note")
	public List<NoteHolder> getNotes(@PathVariable("user") Long user) {
		return service.getNotes(user);
	}

	/**
	 * Add a note for a user
	 * 
	 * @param dto  NoteHolderDTO
	 * @param user user id
	 */
	@PostMapping("/note")
	public void postNotes(@RequestBody NoteHolderDTO dto, @PathVariable("user") Long user) {
		service.add(dto, user);
	}

	/**
	 * Update a note for a user
	 * 
	 * @param dto  NoteHolderDTO
	 * @param user user id
	 */
	@PostMapping("/note/{note}")
	public void updateNote(@RequestBody NoteHolderDTO dto, @PathVariable("user") Long user,
			@PathVariable("note") Long note) {
		service.update(dto, user, note);
	}

	/**
	 * 
	 * @param user user id
	 * @param note note id
	 * @return particular NoteHodler for a user
	 */
	@GetMapping("/note/{note}")
	public NoteHolder getById(@PathVariable(required = true) long user, @PathVariable(required = true) long note) {
		return service.getNoteHolderByUserAndNote(user, note);
	}

	/**
	 * Delete a note
	 * @param user user id
	 * @param note note id
	 */
	@DeleteMapping("/note/{note}")
	public void delete(@PathVariable(required = true) long user, @PathVariable(required = true) long note) {
		service.deleteNoteHolderByNoteAndUser(note, user);
	}
}
