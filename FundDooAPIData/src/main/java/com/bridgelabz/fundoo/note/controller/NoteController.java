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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.env.Environment;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
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
	
	Response response=new Response();

	@PostMapping("/note")
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto,@RequestHeader String token)

       {
		System.out.println("Note Created");
	    Response response= noteService.createNote(noteDto,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
//	
//	@GetMapping("/notes")
//	public String getAllNote()
//	{
//
//		 List<Note> note=noteService.getAllNotes();
//		 System.out.println(note);
//		 return "hiiii";
//		
//	}
	@ResponseBody
	@GetMapping("/note/{id}")
	public List<Note> getNote(@PathVariable(value="id") long userId)
	{
		
		List<Note> response=noteService.getAllNotes(userId);
		 System.out.println(response);
		
	     return response;
		 
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<Response> getByNote(@PathVariable(value="id") long noteId,@RequestHeader String token)
	{
		Response response= noteService.getNoteById(noteId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	@PutMapping("/note/update")
	public ResponseEntity<Response> updateNote(@RequestHeader String token,@Valid @RequestBody Note note)
	{
		
		Response response=noteService.updateNote(note,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	@DeleteMapping("/note/{id}")
	public ResponseEntity<Response> delteNote(@RequestHeader String token,@PathVariable(value="id") long noteId) 
	{
		//System.out.println(token);
		//System.out.println(noteId);
		Response response=noteService.deleteNote(noteId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
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
