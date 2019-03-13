package com.bridgelabz.fundoo.note.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.noteSerivce.NoteService;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.util.Utility;

@Controller
@RequestMapping(value = "/user")
@CrossOrigin(origins="http://localhost:4200")
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	
	@Autowired
    LabelService labelService;
	
	@Autowired
	Environment environment;
	
	Response response=new Response();

	@PostMapping("/note")
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto,@RequestHeader("jwt_token") String token,BindingResult result) throws MethodArgumentNotValidException 

       {
		  if(result.hasErrors())
		    {

				throw new MethodArgumentNotValidException(null,result);
			
		    }
			
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
	public List<Note> getNote(@RequestHeader(value="jwt_token") String token, @RequestParam boolean archived, @RequestParam boolean trashed)
	{
		
		List<Note> response=noteService.getAllNotes(token,archived,trashed);
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
	public ResponseEntity<Response> updateNote(@PathVariable(value="id") long noteId,@RequestHeader(value="jwt_token") String token,@Valid @RequestBody NoteDto noteDto)
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
	
	
//	@PutMapping("/note1/{id}")
//	public ResponseEntity<Response> iArchiveNote(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long noteId)
//	{
//		
//		Response response=noteService.isArchive(token,noteId);
//		
//	     return new ResponseEntity<Response>(response,HttpStatus.OK);
//		 
//	}
	

	
	
	
	
	@PutMapping("/note/isTrash/{id}")
	public ResponseEntity<Response> trashNote(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long noteId,NoteDto1 noteDto)
	{
		
		Response response=noteService.isTrash(noteId,token,noteDto);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	
	@PutMapping("/noted/{id}")
	public ResponseEntity<Response> isArchiveNote(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long noteId,NoteDto1 noteDto)
	{
		
		Response response=noteService.isArchive(noteId,token,noteDto);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	
	@PutMapping("/note/isPin/{id}")
	public ResponseEntity<Response> isPinNote(@RequestHeader(value="jwt_token")  String token,@PathVariable(value="id") long noteId,@RequestParam boolean pin)
	{
		System.out.println("hello");
		Response response=noteService.isPin(noteId,token,pin);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 
	}
	

	@PostMapping("/note/createLabel/{id}")
	public ResponseEntity<?> createLabel(@PathVariable(value="id") long noteId,@Valid @RequestBody LabelDto labelDto,@RequestHeader("jwt_token") String token,BindingResult result) 

       {
		
	    validate(labelDto);
		System.out.println("Label Created");
	    Response response= labelService.createLabel(noteId,labelDto,token);
		
	     return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	

	@PutMapping("/note/editLabel/{id}")
	public ResponseEntity<Response> updateLabel(@PathVariable(value="id") long labelId,@Valid @RequestBody LabelDto labelDto,@RequestHeader("jwt_token") String token,BindingResult result) 

       {
		
	    validate(labelDto);
		System.out.println("Label Created");
	    Response response= labelService.updateLabel(labelId,labelDto,token);
		
	     return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/label/{id}")
	public ResponseEntity<Response> deleteLabel(@RequestHeader(value="jwt_token") String token,@PathVariable(value="id") long labelId) 
	{
		//System.out.println(token);
		//System.out.println(noteId);
		Response response=labelService.deleteLabel(labelId,token);
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	
	@ResponseBody
	@GetMapping("/label/list")
	public List<Label> getLabel(@RequestHeader(value="jwt_token") String token)
	{
		
		List<Label> response=labelService.getAllLabels(token);
		 System.out.println(response);
		
	     return response;
		 
	}
	

	 public void validate(LabelDto labelDto) {
	        if (labelDto.getLabelName().isEmpty()) {
	        	{
                   throw new UserException(101,"invalid date");
	            }
	        }
	}

	
}
