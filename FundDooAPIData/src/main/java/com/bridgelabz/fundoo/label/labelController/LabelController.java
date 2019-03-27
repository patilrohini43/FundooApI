package com.bridgelabz.fundoo.label.labelController;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;


import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.noteSerivce.NoteService;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.util.Utility;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins="http://localhost:4200")
public class LabelController{
	
	@Autowired
	NoteService noteService;
	
	
	@Autowired
    LabelService labelService;
	
	@Autowired
	Environment environment;
	
	Response response=new Response();

	

	@PostMapping("/note/addLabel")
	public ResponseEntity<?> createLabel(@Valid @RequestBody LabelDto labelDto,@RequestHeader("jwt_token") String token,BindingResult result) 

       {
		
	    validate(labelDto);
		System.out.println("Label Created");
	    Response response= labelService.addLabel(labelDto,token);
		
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
	
	
	@DeleteMapping("/label/remove")
	public ResponseEntity<Response> deleteNoteLabel(@RequestParam long noteId,@RequestParam long labelId) 
	{
		//System.out.println(token);
		//System.out.println(noteId);
		Response response=noteService.removeNoteToLabel(noteId,labelId);
		
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
	
	

	@ResponseBody
	@GetMapping("/label/labelNote")
	public List<Note> getLabelNote(@RequestHeader(value="jwt_token") String token,@RequestParam long labelId)
	{
		
		List<Note> response=labelService.labelNote(token,labelId);
		 System.out.println(response);
		
	     return response;
		 
	}

	
	
	@ResponseBody
	@PostMapping("/note/addLabelToNote")
	public ResponseEntity<?> createLabelToNote(@RequestParam long noteId,@RequestParam long labelId) 

       {
		
	   // validate(labelDto);
		System.out.println("Label Created");
	    Response response= noteService.addLabel(noteId,labelId);
		
	     return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	


	 public void validate(LabelDto labelDto) {
	        if (labelDto.getLabelName().isEmpty()) {
	        	{
                   throw new UserException(101,"invalid date");
	            }
	        }
	}
	 
	 
	 
	 
	 

	
}
