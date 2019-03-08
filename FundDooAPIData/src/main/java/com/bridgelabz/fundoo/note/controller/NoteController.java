package com.bridgelabz.fundoo.note.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="http://localhost:4200")
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	Environment environment;
	
	Response response=new Response();

	@PostMapping("/note")
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto,@RequestHeader("jwt_token") String token)

       {
		System.out.println("Note Created");
	    Response response= noteService.createNote(noteDto,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
//	
//	@GetMapping("/notes")
//	public String getAllNote()
//	{

//		 List<Note> note=noteService.getAllNotes();
//		 System.out.println(note);
//		 return "hiiii";
//		
//	}
	@ResponseBody
	@GetMapping("/note/list")
	public List<Note> getNote(@RequestHeader(value="jwt_token") String token)
	{
		
		List<Note> response=noteService.getAllNotes(token);
		 System.out.println(response);
		
	     return response;
		 
	}
	
	
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<Response> getByNote(@PathVariable(value="id") long noteId,@RequestHeader String token)
	{
		Response response= noteService.getNoteById(noteId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	@PutMapping("/note/update/{id}")
	public ResponseEntity<Response> updateNote(@RequestHeader(value="jwt_token") String token,@Valid @RequestBody NoteDto noteDto,@PathVariable(value="id") long noteId)
	{
		
		Response response=noteService.updateNote(noteId,noteDto,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	@DeleteMapping("/note/{id}")
	public ResponseEntity<Response> deleteNote(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long noteId) 
	{
		//System.out.println(token);
		//System.out.println(noteId);
		Response response=noteService.deleteNote(noteId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/note/isTrash/{id}")
	public ResponseEntity<Response> trashNote(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long noteId)
	{
		
		Response response=noteService.isTrash(noteId,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	
	@PutMapping("/note/isArchive/{id}")
	public ResponseEntity<Response> isArchiveNote(@RequestHeader String token,@PathVariable(value="id") long noteId)
	{
		
		Response response=noteService.isArchive(noteId,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	@PutMapping("/note/isPin/{id}")
	public ResponseEntity<Response> isPinNote(@RequestHeader(value="jwt_token")  String token,@PathVariable(value="id") long noteId)
	{
		
		Response response=noteService.isPin(noteId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	

	
}
