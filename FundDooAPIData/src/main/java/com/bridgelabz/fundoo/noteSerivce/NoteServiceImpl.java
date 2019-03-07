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
	
	
	public List<Note> getAllNotes(String token) {
	
		long userId=UserToken.tokenVerify(token);
	 //User user=userRepository.findById(userId)
			 //.orElseThrow(() ->new NoteException(405, environment.getProperty("note.userid.message"),userId));
			 System.out.println("hello");
	List<Note> notes=noteRepository.findAll();
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
   
    
//    public Response deleteNote(long noteId, String token) throws NoteException
//    {
//             
//        	 long userId=UserToken.tokenVerify(token);
//        	 System.out.println(userId);
//        
//        	 
//        
//           Note note=noteRepository.findById(noteId)
//					.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));             
//    //  User user=userRepository.findById(userId).get();  
////      System.out.println("User Details : "+optionalUser.get());
////     	System.out.println(optionalUser.get().getNote());
////     	
////     	Optional<Note> optionalNote = null;
////        for(Note note: optionalUser.get().getNote()) {
////        	if(note.getNoteId()==noteId) {
////        		System.out.println("Working 1");
////        		optionalNote = noteRepository.findById(noteId);
////        		System.out.println(optionalNote.get());
////        		System.out.println(note);
////        		System.out.println("Working 2");
////        		noteRepository.delete(note);
////        		break;
////        	}
////        }
//     
//	    // user.getNote().forEach(data -> {
//	    //	 if(((long)(data.getNoteId())) == (noteId)) 
//	    //	 {
//	    		 
//	    	//	 
//	    	//	 noteRepository.deleteById(noteId);
//	    	//	 System.out.println(noteId);
////    		   System.out.println("Hello");
////    		   Optional<Note> optionalNote = noteRepository.findById(noteId);
////    	       noteRepository.delete(optionalNote.get());    	 
////    		  
//       // }
//	   //  });
//	     
//     	noteRepository.delete(note);
//     			  
//             Response response=Utility.statusResponse(401, environment.getProperty("note.delete.message"));
//		      return response;
//	
//    }
//    
//    
    
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
    
    
    public Response isTrash(long noteId,String token)
    
    {
    	  long userId=UserToken.tokenVerify(token);
          System.out.println(userId);

        Note note=noteRepository.findById(noteId)
        		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
    	

        System.out.println(note.isTrash());
        if(note.isTrash())
    	{
    		note.setTrash(false);
    	}
        else
        {
        	note.setTrash(true);
        }
        
        Response response=Utility.statusResponse(401, environment.getProperty("note.isTrash.message"));
	      return response;
    	
    }
    
    
public Response isArchive(long noteId,String token)
    
    {
    	  long userId=UserToken.tokenVerify(token);
          System.out.println(userId);

        Note note=noteRepository.findById(noteId)
        		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
    	

        System.out.println(note.isArchive());
        if(note.isArchive())
    	{
    		note.setArchive(false);
    	}
        else
        {
        	note.setArchive(true);
        }
        
        Response response=Utility.statusResponse(401, environment.getProperty("note.isArchive.message"));
	      return response;
    	
    }

public Response isPin(long noteId,String token)

{
	  long userId=UserToken.tokenVerify(token);
      System.out.println(userId);

    Note note=noteRepository.findById(noteId)
    		.orElseThrow(() -> new NoteException(405, environment.getProperty("note.id.message"),noteId));        
	

    System.out.println(note.isPin());
    if(note.isPin())
	{
		note.setPin(false);
	}
    else
    {
    	note.setPin(true);
    }
    
    Response response=Utility.statusResponse(401, environment.getProperty("note.isPin.message"));
      return response;
	
}







   
}
