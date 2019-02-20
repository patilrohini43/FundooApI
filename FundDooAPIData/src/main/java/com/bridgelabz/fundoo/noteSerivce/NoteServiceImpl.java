package com.bridgelabz.fundoo.noteSerivce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.util.UserToken;



@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	

	public void createNote(NoteDto noteDto,String token) throws Exception
	{
		
		Note note=modelMapper.map(noteDto, Note.class);
		long userId=UserToken.tokenVerify(token);
		note.setUserId(userId);
	    noteRepository.save(note);
		
	  }
		
		
	
	public List<Note> getAllNotes() {
		
	    return noteRepository.findAll();
	    
	}
	
	 
    public boolean getNoteById(Long noteId) throws UserException
    {
    	Note check=noteRepository.findById(noteId)
    	.orElseThrow(() -> new UserException("Note Not Found"));
    	if(check!=null)
    	{
    		return true;
    	}
    	
 		return false;
    	
    }

	
    
    
    


}
