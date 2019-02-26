package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;



@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	
	@Autowired
	private Environment environment;
	

	public Response createNote(NoteDto noteDto,String token) 
	{
		
		Note note=modelMapper.map(noteDto, Note.class);
		long userId=UserToken.tokenVerify(token);
		note.setUserId(userId);
		note.setCreateDate(LocalDateTime.now());
	    Note check= noteRepository.save(note);
	    
	    Response response=Utility.statusResponse(401, environment.getProperty("note.success.message"));
		return response;
	  
		
	  }
		
		
	
	public List<Note> getAllNotes() {
		
	    return noteRepository.findAll();
	    
	}
	
	 
    public boolean getNoteById(Long noteId)
    {
    	Note check=noteRepository.findById(noteId)
    	.orElseThrow(() -> new UserException("Note Not Found"));
    	if(check!=null)
    	{
    		return true;
    	}
    	
 		return false;
    	
    }
    
    
    
    public Response updateNote(Note note,String token)
    {
    	//Note notes=noteRepository.findById(noteId)
    			//.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message")));
    	long userId=UserToken.tokenVerify(token);
    	note.setUserId(userId);
  
        note.setUpdatedDate(LocalDateTime.now());
        
      Note updateNote=noteRepository.save(note);
      Response response=Utility.statusResponse(401, environment.getProperty("note.update.message"));
      return response;
    }
    
    public Response deleteNote(long noteId,String token)
    {
             System.out.println(noteId);
			long userId=UserToken.tokenVerify(token);
			Note notes=noteRepository.findById(noteId)
					.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));
	    
             noteRepository.delete(notes);
             
             Response response=Utility.statusResponse(401, environment.getProperty("note.delete.message"));
    	  
		      return response;
	
    }
    
    

}
