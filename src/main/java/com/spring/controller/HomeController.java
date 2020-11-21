package com.spring.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.model.RestNote;
import com.spring.service.RestNoteService;

@RestController
@SessionAttributes("noteInSession")
public class HomeController {
	@Autowired
	RestNoteService noteService;
	
	@GetMapping(value="/")
	public String getMessage() {
		return "Welcome to Spring Notes";
	}
	
	@ResponseBody
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestNote>> listAllUsers(HttpServletResponse res) {
		List<RestNote> notes = noteService.findAllRestNotes();
		
		if (notes.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<RestNote>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RestNote>>(notes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/note/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestNote> getNote(@PathVariable("noteId") int noteId) {
		System.out.println("Fetching Note with id " + noteId);
		RestNote note = noteService.findById(noteId);
		if (note == null) {
			System.out.println("User with id " + noteId + " not found");
			return new ResponseEntity<RestNote>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RestNote>(note, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.POST)
	public ResponseEntity<Void> createNote(@RequestBody RestNote note, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + note.getName());

		noteService.saveRestNote(note);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/note/{id}").buildAndExpand(note.getNoteId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/note/delete/{no}")
	public ResponseEntity<Void> deleteUser(@PathVariable ("no") int no) {
	noteService.deleteRestNoteById(no);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/note/update")
	public ResponseEntity<Boolean> updateNote(@RequestBody RestNote noteRec) {
		noteService.updateRestNote(noteRec);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "/note/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestNote>> findByCategory(@PathVariable("category") String category) {
		System.out.println("Fetching notes that are " + category);
		List<RestNote> note = noteService.findByCategory(category);
		if (note == null) {
			System.out.println("Note that is " + category + " not found");
			return new ResponseEntity<List<RestNote>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RestNote>>(note, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestNote>> findByName(@PathVariable("name") String name) {
		List<RestNote> noteName = noteService.findByName(name);
		
		if (noteName.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<RestNote>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RestNote>>(noteName, HttpStatus.OK);
	}
	
	@DeleteMapping("/note/delete/{no}")
	public ResponseEntity<Boolean> deleteNote2(@PathVariable ("noteId") RestNote noteId) {
	noteService.deleteNote(noteId);
		return new ResponseEntity(HttpStatus.OK);
	}

}
