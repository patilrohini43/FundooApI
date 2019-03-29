package com.bridgelabz.fundoo.note.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private Environment environment;

	public Response createNote(NoteDto noteDto,String token) 
	{
		
		Long userId=UserToken.tokenVerify(token);
		Note note=modelMapper.map(noteDto, Note.class);
		
		User user=new User(userId);
		note.setUser(user);
//           
//		User user=userRepository.findById(userId)
//				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
//			
//		//User user=new User(userId);
//		user.getNote().add(note);
//		
//		userRepository.save(user);
	    noteRepository.save(note);

	    Response response=Utility.statusResponse(401, environment.getProperty("note.success.message"));
		return response;
	  
		
	  }
	
	
	
	
	
	

		

//	public List<Note> getAllNotes(Long userId) {
//		
//		
//		 User user=userRepository.findById(userId)
//				 .orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
//				 
//		// List<Note> notes=user.getNote();
//	   
//		  System.out.println(notes);
//	     //Response response=Utility.statusResponseNote2(401, environment.getProperty("note.id.sucess"),notes);
//		return notes;
//	    
//	}
	
	
	public List<Note> getAllNotes(String token,boolean archived,boolean trashed) {
	
		long userId=UserToken.tokenVerify(token);
	 //User user=userRepository.findById(userId)
			 //.orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
			 System.out.println("hello");
	List<Note> notes=noteRepository.findAll().stream()
			  .filter(note-> note.getUser().getId().equals(userId)
					  	&& note.isArchive()==archived
					  	&& note.isTrash()==trashed)
.collect(Collectors.toList());
	
	List<Note> list = new ArrayList<>();
	
	for (int i = 0; i < notes.size(); i++) {
		
		if(notes.get(i).getUser().getId()==userId)
		{
			
			list.add(notes.get(i));
			System.out.println(list.add(notes.get(i)));
		
	    }
	}
   
	  System.out.println(notes);
     //Response response=Utility.statusResponseNote2(401, environment.getProperty("note.id.sucess"),notes);
	return notes;
    
}


//	public List<Note> getAllNote(String token) 
//	{
//	Long userId=UserToken.tokenVerify(token);
//	List<Note> notelist=noteRepository.findAll();
//	
//	List<Note> list = new ArrayList<>();
//	for (int i = 0; i < notelist.size(); i++) {
//		
//		if(notelist.get(i).getUser().getId()==userId)
//		{
//			list.add(notelist.get(i));
//		
//	}
//	}
//	
//		return list;
//
//}


	
	 
    public Response getNoteById(long noteId,String token)
    {
    	long userId=UserToken.tokenVerify(token);
        Note note=noteRepository.findById(noteId)
    			.orElseThrow(() -> new UserException("Note Not Found"));
 
      Response response=Utility.statusResponseNote1(401, environment.getProperty("note.id.sucess"),note);
    	return response;
    }
    
    
    public Response updateNote(long noteId,NoteDto noteDto,String token)
    {
    	
    	 long userId=UserToken.tokenVerify(token);
       
 
    	Note note=noteRepository.findById(noteId)
				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));
    
    	
    	
      note.setTitle(noteDto.getTitle());
      note.setDescription(noteDto.getDescription());
      note.setColor(noteDto.getColor());
      note.setUpdatedDate(LocalDateTime.now());
      long dbUserId=note.getUser().getId();
      System.out.println(dbUserId);
    //  System.out.println(user.getId());
      if(note.isPresent() && dbUserId==userId)
      {
      	noteRepository.save(note);
      }
	
      Response response=Utility.statusResponse(401, environment.getProperty("note.update.message"));
      return response;
    }
   
    
    
    public Response deleteNote(long noteId, String token) 
    {
    	 
            long userId=UserToken.tokenVerify(token);
            System.out.println(userId);
        
 
       
            Note note=noteRepository.findById(noteId)
            		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
            System.out.println(note);
          
            long dbUserId=note.getUser().getId();
            
            System.out.println(dbUserId);
          //  System.out.println(user.getId());
            if(note.isPresent()||dbUserId==userId)
            {
            	noteRepository.delete(note);
            }
			  
             Response response=Utility.statusResponse(401, environment.getProperty("note.delete.message"));
		      return response;
	
    }
    
    
    public Response isTrash(long noteId,String token,NoteDto1 noteDto)
    
    {
    	  long userId=UserToken.tokenVerify(token);
          System.out.println(userId);

        Note note=noteRepository.findById(noteId)
        		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
    	

        System.out.println(note.isTrash());
        if(note.isTrash())
    	{
    		note.setTrash(false);
    		 Response response=Utility.statusResponse(401, environment.getProperty("note.isunTrash.message"));
   	      return response;
    		
    	}
        else
        {
        	note.setTrash(true);
        	 Response response=Utility.statusResponse(401, environment.getProperty("note.isTrash.message"));
   	      return response;
        	
        }
        
       
       
    	
    }
    
    
public Response isArchive(long noteId,String token,NoteDto1 noteDto)
    
    {
    	  long userId=UserToken.tokenVerify(token);
          System.out.println(userId);

        Note note=noteRepository.findById(noteId)
        		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
    	

        System.out.println(note.isArchive());
        if(note.isArchive())
    	{
    		note.setArchive(false);
    		 noteRepository.save(note);
    		  Response response=Utility.statusResponse(401, environment.getProperty("note.isUNArchive.message"));
    	  	  return response;
    	}
        else
        {
        note.setArchive(true);
        noteRepository.save(note);
      
  	   Response response=Utility.statusResponse(401, environment.getProperty("note.isArchive.message"));
	     return response;
        }
        
       
        
        
    	
    }

public Response isPin(long noteId,String token,boolean pin)

{
	System.out.println("hello");
	  long userId=UserToken.tokenVerify(token);
      System.out.println(userId);

    Note note=noteRepository.findById(noteId)
    		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
	

    System.out.println(note.isPin());
    if(note.isPin())
	{
	 note.setPin(false);
	 Response response=Utility.statusResponse(401, environment.getProperty("note.isPin.message"));
		return response;
	}
    else
    {
     note.setPin(true);
     Response response=Utility.statusResponse(401, environment.getProperty("note.isUnPin.message"));
     return response;
    }
    
   
     
	
}



//public Response addLabel(long noteId,long labelId)
//{
//	
//	
//	   noteRepository.findById(noteId).map(note ->{
//		Label label=labelRepository.findById(labelId).get();
//		note.getLabels().add(label);
//
//		System.out.println(note);
//		return note;
//	
//	}).ifPresent(noteRepository::save);
//			
//
//Response response=Utility.statusResponse(401, environment.getProperty("note.LabelToNote.message"));
//return response;
//	
//}



public Response addLabel(long noteId,long labelId)
{
	System.out.println("hello");
	//long userId=UserToken.tokenVerify(token);
	
	Note note=noteRepository.findById(noteId).get();
	Label label=labelRepository.findById(labelId).get();
	note.getLabel().add(label);
	label.getNotes().add(note);
	
	noteRepository.save(note);

    Response response=Utility.statusResponse(401, environment.getProperty("Label.success.message"));
    return response;
	
}

public Response removeNote(Note note,Label label)
{
	label.getNotes().remove(note);
	noteRepository.save(note);
	 Response response=Utility.statusResponse(401, environment.getProperty("Label.remove.message"));
	    return response;
}


public Response removeNoteToLabel(long noteId,long labelId)
{
	System.out.println("hello");
	//long userId=UserToken.tokenVerify(token);
	
	Note note=noteRepository.findById(noteId).get();
	Label label=labelRepository.findById(labelId).get();
	note.getLabel().remove(label);
	label.getNotes().remove(note);
	
	noteRepository.save(note);

	labelRepository.save(label);
    Response response=Utility.statusResponse(401, environment.getProperty("Label.remove.message"));
    return response;
	
}

//
//@Override
//public List<Label> getAllNoteLabel(long noteId) {
//	// TODO Auto-generated method stub
//	
//	Optional<Note> userNote = noteRepository.findById(noteId);
//	Note note = userNote.get();
//	List<Label> label = note.getLabel();
//	return label;
//}










   
}
