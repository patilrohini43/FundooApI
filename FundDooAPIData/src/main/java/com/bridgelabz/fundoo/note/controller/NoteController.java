package com.bridgelabz.fundoo.note.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.env.Environment;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.noteSerivce.NoteService;

@Controller
@RequestMapping(value = "/user")
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	Environment environment;

	@PostMapping("/createNote")
	public ResponseEntity<String> createNote(@Valid @RequestBody NoteDto noteDto,@PathVariable String token) throws Exception

       {
		
		 boolean check=noteService.createNote(noteDto,token);
		 if(check==true)
		 {
	     return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
		 }else
		
		   return new ResponseEntity<String>(environment.getProperty("data"),HttpStatus.OK);
			
		
	}
	
	@GetMapping("/getNote")
	public List<Note> getAllNote()
	{
		return noteService.getAllNotes();
		
	}
	
	@PutMapping("/updateNote/{id}")
	public ResponseEntity<String> updateNote(@PathVariable(value="id") long noteId,@Valid @RequestBody Note note) throws UserException
	{
		
		boolean check=noteService.updateNote(note,noteId);
		 if(check)
		 {
	     return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
		 }

		return new ResponseEntity<String>(environment.getProperty("data"),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteNote/{id}")
	public ResponseEntity<String> delteNote(@PathVariable(value="id") long noteId) throws UserException
	{
		
		noteService.deleteNote(noteId);
		
		 return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
		
	}
	
	
//	@PutMapping("/updateNote1/{id}")
//	public ResponseEntity<String> updateNote1(@PathVariable(value="id") long noteId,@Valid @RequestBody NoteDto noteDto) throws UserException
//	{
//		
//		boolean check=noteService.registerUser(noteDto,noteId);
//		 if(check)
//		 {
//	     return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
//		 }
//
//		return new ResponseEntity<String>(environment.getProperty("data"),HttpStatus.OK);
//		
//	}
//	
//	
	
	

}
