package com.bridgelabz.fundoo.label.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;

@Service
public class LabelServiceImpl implements LabelService{
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private Environment environment;
	
	public Response createLabel(long noteId,LabelDto labelDto,String token)
	{
		System.out.println("hello");
		long userId=UserToken.tokenVerify(token);
		Label label=modelMapper.map(labelDto, Label.class);
		//Note note=new Note(noteId);
		//label.setNote(note);
		///note.setNoteId(noteId);
		//label.setUserId(userId);
		//long dbUserId=label.getUser().setId(userId);
		//System.out.println(dbUserId);
		labelRepository.save(label);

        Response response=Utility.statusResponse(401, environment.getProperty("Label.success.message"));
        return response;
		
	}
	
	
	public Response addLabel(LabelDto labelDto,String token)
	{
		System.out.println("hello");
		long userId=UserToken.tokenVerify(token);
		Label label=modelMapper.map(labelDto, Label.class);
		//label.setUserId(userId);
	   // long dbUserId=label.getUser().setId(userId);
		User user=new User(userId);
		label.setUser(user);
		
		System.out.println("helloq");
		labelRepository.save(label);

        Response response=Utility.statusResponse(401, environment.getProperty("Label.success.message"));
        return response;
		
	}
	
	
	
	public Response updateLabel(long labelId,LabelDto labelDto,String token)
	{
		long userId=UserToken.tokenVerify(token);
	
		Label label=labelRepository.findById(labelId)
				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),labelId));
		
	    label.setLabelName(labelDto.getLabelName());
	    long dbUserId=label.getUser().getId();
	    System.out.println(dbUserId);
	    
	    if(dbUserId==userId)
	    {
	    	labelRepository.save(label);
	    }
		

        Response response=Utility.statusResponse(401, environment.getProperty("Label.success1.message"));
        return response;
		
	}
	
	
	
	public Response deleteLabel(long labelId,String token)
	{
		
		   long userId=UserToken.tokenVerify(token);
           System.out.println(userId);
       

      
           Label label=labelRepository.findById(labelId)
           		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),labelId));        
           System.out.println(label);
         
           long dbUserId=label.getUser().getId();
           
           System.out.println(dbUserId);
         //  System.out.println(user.getId());
           if(label.isPresent()||dbUserId==userId)
           {
           	labelRepository.delete(label);
           }
		   Response response=Utility.statusResponse(401, environment.getProperty("Label.delete.message"));
	        return response;
	}
	
	
	

	public List<Label> getAllLabels(String token) {
	
		long userId=UserToken.tokenVerify(token);
	 //User user=userRepository.findById(userId)
			 //.orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
			 System.out.println("hello");
	List<Label> notes=labelRepository.findAll();
	
	List<Label> list = new ArrayList<>();
	
	for (int i = 0; i < notes.size(); i++) {
		
		long dbuser=notes.get(i).getUser().getId();
		
		if(dbuser==userId)
		{
			list.add(notes.get(i));
			System.out.println(list.add(notes.get(i)));
		
	    }
	}
   
	  System.out.println(notes);
     //Response response=Utility.statusResponseNote2(401, environment.getProperty("note.id.sucess"),notes);
	return notes;
    
}


	
	
	

}
