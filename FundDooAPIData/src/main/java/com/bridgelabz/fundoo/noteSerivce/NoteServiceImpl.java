package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;



@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	

	public boolean createNote(NoteDto noteDto,String token) 
	{
		
		Note note=modelMapper.map(noteDto, Note.class);
		long userId=UserToken.tokenVerify(token);
		note.setUserId(userId);
	    Note check= noteRepository.save(note);
	   if(check != null)
	   {
		   return true;
	   }
	   
	   return false;
		
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
    
    
    
    public boolean updateNote(Note note,String token)
    {
    
    	//Note notes=noteRepository.findById(noteId)
    			//.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    	long userId=UserToken.tokenVerify(token);
    	note.setUserId(userId);
    	//notes.setTitle(note.getTitle());
        //notes.setDescription(note.getDescription());   
        note.setUpdatedDate(LocalDateTime.now());
        
      Note updateNote=noteRepository.save(note);
      if(updateNote!=null)
	   {
		   return true;
	   }
	   
	   return false;
    
    }
    
    public boolean deleteNote(long noteId,String token)
    {
    
			long userId=UserToken.tokenVerify(token);
			Note notes=noteRepository.findById(noteId)
					.orElseThrow(() ->new NoteException("Note", "id", noteId));
	    
             noteRepository.delete(notes);
    	  
		return false;
	
    }
    
    

}
