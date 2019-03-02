package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private Environment environment;

	public Response createNote(NoteDto noteDto,String token) 
	{
		
		Long userId=UserToken.tokenVerify(token);
		Note notes=modelMapper.map(noteDto, Note.class);
		
		//User user=new User(userId);
		//notes.setUser(user);
           
		User user=userRepository.findById(userId)
				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
			
		//User user=new User(userId);
		user.getNote().add(notes);
		
		userRepository.save(user);
	  

	    Response response=Utility.statusResponse(401, environment.getProperty("note.success.message"));
		return response;
	  
		
	  }
		
		

	public List<Note> getAllNotes(Long userId) {
		
		
		 User user=userRepository.findById(userId)
				 .orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
				 
		 List<Note> notes=user.getNote();
	   
		  System.out.println(notes);
	     //Response response=Utility.statusResponseNote2(401, environment.getProperty("note.id.sucess"),notes);
		return notes;
	    
	}
	
	
	
	 
    public Response getNoteById(long noteId,String token)
    {
    	long userId=UserToken.tokenVerify(token);
        Note note=noteRepository.findById(noteId)
    			.orElseThrow(() -> new UserException("Note Not Found"));
 
      Response response=Utility.statusResponseNote1(401, environment.getProperty("note.id.sucess"),note);
    	return response;
    }
    
    
    public Response updateNote(Note note,String token)
    {
    	
    	long userId=UserToken.tokenVerify(token);
         User user=userRepository.findById(userId)
    	
        		 .orElseThrow(() -> new NoteException(405, environment.getProperty("note.userid.message"),userId));
    
        Long noteId=note.getNoteId();
    	Note notes=noteRepository.findById(noteId)
				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));
    
   
      note.setUpdatedDate(LocalDateTime.now());
	  noteRepository.save(note);
	
      Response response=Utility.statusResponse(401, environment.getProperty("note.update.message"));
      return response;
    }
   
    
    public Response deleteNote(long noteId, String token) throws NoteException
    {
             
        	 long userId=UserToken.tokenVerify(token);
        	 System.out.println(userId);
        
        	 
        
           Note note=noteRepository.findById(noteId)
					.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));             
    //  User user=userRepository.findById(userId).get();  
//      System.out.println("User Details : "+optionalUser.get());
//     	System.out.println(optionalUser.get().getNote());
//     	
//     	Optional<Note> optionalNote = null;
//        for(Note note: optionalUser.get().getNote()) {
//        	if(note.getNoteId()==noteId) {
//        		System.out.println("Working 1");
//        		optionalNote = noteRepository.findById(noteId);
//        		System.out.println(optionalNote.get());
//        		System.out.println(note);
//        		System.out.println("Working 2");
//        		noteRepository.delete(note);
//        		break;
//        	}
//        }
     
	    // user.getNote().forEach(data -> {
	    //	 if(((long)(data.getNoteId())) == (noteId)) 
	    //	 {
	    		 
	    	//	 
	    	//	 noteRepository.deleteById(noteId);
	    	//	 System.out.println(noteId);
//    		   System.out.println("Hello");
//    		   Optional<Note> optionalNote = noteRepository.findById(noteId);
//    	       noteRepository.delete(optionalNote.get());    	 
//    		  
       // }
	   //  });
	     
     	noteRepository.delete(note);
     			  
             Response response=Utility.statusResponse(401, environment.getProperty("note.delete.message"));
		      return response;
	
    }
    
    
    
    public Response deleteNote1(long noteId, String token) 
    {
    	 
            long userId=UserToken.tokenVerify(token);
            System.out.println(userId);
        
            User user=new User();
            Note note=noteRepository.findById(noteId)
            		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
            System.out.println(note);
//          
//           // long id=user.getId();
//            //System.out.println(id);
            System.out.println(user.getId());
//            if(note.isPresent()&&dbUserId==userId&&note.isTrash()==true)
//            {
//            	noteRepository.delete(note);
//            }
			  
             Response response=Utility.statusResponse(401, environment.getProperty("note.delete.message"));
		      return response;
	
    }
   
   
}
