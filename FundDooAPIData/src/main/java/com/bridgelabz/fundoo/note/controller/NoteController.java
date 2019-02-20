package com.bridgelabz.fundoo.note.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<String> createNote(@Valid @RequestBody NoteDto noteDto,@RequestParam String token) throws Exception

       {
		
		 noteService.createNote(noteDto,token);
	   
	       
       return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
	
		
	}
	
	@GetMapping("/getNote")
	public List<Note> getAllNote()
	{
		return noteService.getAllNotes();
		
	}
	
	

}
