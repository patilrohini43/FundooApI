package com.bridgelabz.fundoo.collabrator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.collabrator.service.CollabratorService;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.noteSerivce.NoteService;
import com.bridgelabz.fundoo.user.model.Response;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins="http://localhost:4200")
public class CollabratorController {
	

	@Autowired
	CollabratorService collabratorService;

	@Autowired
	Environment environment;
	
	Response response=new Response();
	
	

	@PostMapping("/collabrator/addCollabrator")
	public ResponseEntity<?> createLabel(@RequestHeader(value="jwt_token") String token,@RequestParam long noteId,@RequestParam long userId) 

       {
		
	 
		System.out.println("Collbrator Created");
	    Response response=collabratorService.addCollabrator(token,noteId, userId);
	     return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	
	
//	@GetMapping("collabrator/getList")
//	public  ResponseEntity<List<Note>> getCollabratorNotes(@RequestHeader(value="jwt_token") String token){
//		
//		List<Note> list=collabratorService.getCollabratorNotes(token);
//		
//		 return new ResponseEntity<>(list,HttpStatus.OK);	
//		
//	}
//	


	@PostMapping("/collabrator/addCollab")
	public ResponseEntity<?> addCollab(@RequestHeader(value="jwt_token") String token,@RequestParam long noteId,@RequestParam String email) 

       {
		
	 
		System.out.println("Collbrator Created");
	    Response response=collabratorService.add(token, noteId, email);
	     return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	
	@PostMapping("/collabrator/delete")
	public ResponseEntity<?> delete(@RequestHeader(value="jwt_token") String token,@RequestParam long noteId,@RequestParam String email) 

       {
		
	 
		System.out.println("Collbrator Created");
	    Response response=collabratorService.removeCollbrator(noteId,token,email);
	     return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	
	
	

}
