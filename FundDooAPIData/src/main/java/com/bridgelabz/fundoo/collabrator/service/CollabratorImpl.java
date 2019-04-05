package com.bridgelabz.fundoo.collabrator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundoo.collabrator.model.Collabrator;
import com.bridgelabz.fundoo.collabrator.repository.CollabratorRepository;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;

@Service
public class CollabratorImpl implements CollabratorService{
	
	
	@Autowired
	private CollabratorRepository collabratorRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	private Environment environment;
	
	
	
	@Override
	public Response addCollabrator(String token,long noteId, long userId) {
		// TODO Auto-generated method stub
	 	
	     long userId1=UserToken.tokenVerify(token);
		
           Optional<Long> collabrator=collabratorRepository.findByValue(noteId, userId);
        		   
           if(collabrator.isPresent())
           {
        	   Response response=Utility.statusResponse(401, environment.getProperty("collabrator.error.message"));
 		        return response;
           }
	      
	      
		   Collabrator collabrator1=new Collabrator(noteId,userId);
		   collabratorRepository.save(collabrator1);
		   Response response=Utility.statusResponse(401, environment.getProperty("collabrator.success.message"));
	        return response;
	      
	
	}
	



//	public List<Note> getCollabratorNotes(String token)
//	{
//		long userId=UserToken.tokenVerify(token);
//		Optional<List<Long>> collab=collabratorRepository.findNoteAllById(userId);
//		System.out.println(collab);
//		if(collab.isPresent())
//		{
//	        return noteRepository.findCollabratorNotes(collab.get()).get();
//			
//		}
//		
//		return new ArrayList<Note>();
//		
//	}
	
	
	
	public Response removeCollbrator(long noteId,String token,String email)
	{
		long userId=UserToken.tokenVerify(token);
		User user=userRepository.findById(userId).get();
		Note note=noteRepository.findById(noteId).get();
		
		List<User> uservalue=note.getCollabuser().stream().filter(e-> e.getEmail().equals(email)).collect(Collectors.toList());
		Collabrator collabrator=collabratorRepository.findById(noteId).get();
		collabratorRepository.delete(collabrator);
		  Response response=Utility.statusResponse(401, environment.getProperty("collabrator.success.message"));
	        return response;
	}
	
	
	
	
	
	
	
	
	public Response add(String token,long noteId,String email)
	{
		long userId=UserToken.tokenVerify(token);
		User userData=userRepository.findById(userId).get();
		User userEmail=userRepository.findUserByEmail(email).get();
		Note note=noteRepository.findById(noteId).get();
		userData.getEmail().equals(email);
		
		List<User> uservalue=note.getCollabuser().stream().filter(e-> e.getEmail().equals(email)).collect(Collectors.toList());
		System.out.println("hello");
		System.out.println(uservalue);
		if(uservalue.isEmpty()&& userData.getId().equals(userId))
		{
		userData.getCollabnote().add(note);
		note.getCollabuser().add(userData);
		userRepository.save(userData);
		noteRepository.save(note);
		Response response=Utility.statusResponse(401, environment.getProperty("collabrator.success.message"));
	    return response;
		}
		else
		{
			Response response=Utility.statusResponse(401, environment.getProperty("collabrator.error.message"));
		    return response;
		}
	   

	
	
   
	
	
	
	
	}
	
	
}
	
	
	
	
	
	
	