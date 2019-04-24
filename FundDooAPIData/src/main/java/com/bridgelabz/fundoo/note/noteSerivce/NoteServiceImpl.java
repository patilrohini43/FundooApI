package com.bridgelabz.fundoo.note.noteSerivce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

import com.bridgelabz.fundoo.elasticSearch.ElasticSearch;
import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.rabbitmq.MessageProducer;
import com.bridgelabz.fundoo.rabbitmq.NoteContainer;
import com.bridgelabz.fundoo.rabbitmq.NoteOperation;
import com.bridgelabz.fundoo.rabbitmq.RabbitMqBody;
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
	private MessageProducer producer;

	

	@Autowired
	private ElasticSearch elasticSearch;


	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;
	@Autowired
	private Environment environment;

	public Response createNote(NoteDto noteDto,String token) throws IOException 
	{

		Long userId=UserToken.tokenVerify(token);
		Note note=modelMapper.map(noteDto, Note.class);
       Optional<User> user=userRepository.findById(userId);
		//User user=new User(userId);
		System.out.println("hello"+user);
	//	note.setUser(user);
		note.setUser(user.get());
		//note.setCreateDate(LocalDateTime.now());
		//note.setUpdatedDate(LocalDateTime.now());
				note=noteRepository.save(note);
		
		System.out.println("hjhg");		
		System.out.println(note);
		
		NoteContainer noteContainer=new NoteContainer();
		noteContainer.setNote(note);
		noteContainer.setNoteoperation(NoteOperation.CREATE);
		producer.sendNote(noteContainer);
		System.out.println("welcome");
		//elasticSearch.save(note);

		Response response=Utility.statusResponse(401, environment.getProperty("note.success.message"));
		return response;


	}

	public Response ReminderSet(long noteId,String time) throws ParseException 
	{
		//Long userId=UserToken.tokenVerify(token);


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Date date =dateFormat.parse(time);

		Note note=noteRepository.findById(noteId).get();
		note.setReminder(date);
		noteRepository.save(note);
		Response response=Utility.statusResponse(401, environment.getProperty("note.reminder.message"));
		return response;


	}


	public Response ReminderRemove(long noteId,String time) 
	{
		//Long userId=UserToken.tokenVerify(token);

		Note note=noteRepository.findById(noteId).get();
		note.setReminder(null);
		noteRepository.save(note);
		Response response=Utility.statusResponse(401, environment.getProperty("note.removeReminder.message"));
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

		List<Note> sharedNotes = userRepository.findById(userId).get().getCollabnote().stream().collect(Collectors.toList());

		List<Note> list = new ArrayList<>();
		notes.addAll(sharedNotes);


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


	public Response updateNote(long noteId,NoteDto noteDto,String token)
	{
		long userId=UserToken.tokenVerify(token);
		Note note=noteRepository.findById(noteId)
				.orElseThrow(() ->new NoteException(405, environment.getProperty("note.id.message"),noteId));
		note.setTitle(noteDto.getTitle());
		note.setDescription(noteDto.getDescription());
		note.setColor(noteDto.getColor());
		note.setUpdatedDate(LocalDateTime.now());
		// note.setReminder(LocalDateTime.now());

		long dbUserId=note.getUser().getId();
		System.out.println(dbUserId);
		if(dbUserId==userId)
		{
			note = noteRepository.save(note);
			elasticSearch.updateNote(note);
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
		if(dbUserId==userId)
		{
		noteRepository.delete(note);
		 elasticSearch.deleteNote(noteId);
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
		// stream the list and use the set to filter it
		List<Label> labels = note.getLabel().stream()
				.filter(e -> (e.getLabelId()==labelId))
				.collect(Collectors.toList());


		System.out.println(labels);
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




	public Response changeColor(long noteId,NoteDto dto,String token) {

		long userId=UserToken.tokenVerify(token);
		Note note=noteRepository.findById(noteId).get();
		note.setColor(dto.getColor());
		Response response=Utility.statusResponse(401, environment.getProperty("note.color.message"));
		return response;

	}





	public Response add(String token,long noteId,String email)
	{
		System.out.println("hello");
		long userId=UserToken.tokenVerify(token);

		Note note=noteRepository.findById(noteId).get();
		User user=userRepository.findById(userId).get();
		//User userid=userRepository.findByEmail(email);
		User userid=userRepository.findUserByEmail(email).orElseThrow(()->
		new UserException(401,"This Email Id Not Exist"));
		boolean useremail=user.getEmail().equals(email);


		//	if(useremail)
		//	{
		//		throw new UserException(302,"Email Id Not Present");
		//	}
		// stream the list and use the set to filter it
		List<User> uservalue = note.getCollabuser().stream()
				.filter(e -> (e.getEmail().equals(email)))
				.collect(Collectors.toList());


		System.out.println(note.getCollabuser());
		System.out.println("collab user"+uservalue);
		if(uservalue.isEmpty()&& !useremail)
		{
			System.out.println("hello");

			note.getCollabuser().add(userid);

			userid.getCollabnote().add(note);
			User value=userRepository.save(userid);
			System.out.println("check"+value);
			String url =this.getUrl("loginVerify", user.getId());
			RabbitMqBody messageBody = new RabbitMqBody();
			messageBody.setEmailId(email);
			messageBody.setSubject("Successfully send");
			messageBody.setUrl(email+"...Sending Note To You..Check Your Google Keep");
			//send messageBody to rabbitMq queue
			//	producer.sendMessage("hiii");
			producer.sendMessage(messageBody);
			noteRepository.save(note);
			Response response=Utility.statusResponse(401, environment.getProperty("collabrator.success.message"));
			return response;
		}

		else
		{
			Response response=Utility.statusResponse(403, environment.getProperty("collabrator.error.message"));
			return response;
		}




	}



	public String getUrl(String service, Long id) {

		return "http://localhost:4200/" + service 
				+ "/" + UserToken.createToken(id);

	}


	public Response removeCollbrator(String token,long noteId,String email)
	{
		System.out.println("hello");
		long userId=UserToken.tokenVerify(token);

		Note note=noteRepository.findById(noteId).get();
		User user=userRepository.findById(userId).get();
		User userid=userRepository.findByEmail(email);
		boolean useremail=user.getEmail().equals(email);

		// stream the list and use the set to filter it
		List<User> uservalue = note.getCollabuser().stream()
				.filter(e -> (e.getEmail().equals(email)))
				.collect(Collectors.toList());


		System.out.println(note.getCollabuser());
		System.out.println("collab user"+uservalue);
		if(!uservalue.isEmpty())
		{
			System.out.println("hello");

			note.getCollabuser().remove(userid);
			boolean userss= userid.getCollabnote().remove(note);
			System.out.println(userss);

			User value=userRepository.save(userid);
			System.out.println("check"+value);
			noteRepository.save(note);

		}


		Response response=Utility.statusResponse(401, environment.getProperty("collabrator.success.remove.message"));
		return response;

	}







	public List<Note> getCollabratorNotes(String token)
	{
		Long userId=UserToken.tokenVerify(token);
		Optional<List<Long>> collab=noteRepository.findNoteAllById(userId);
		//Optional<List<Long>> collab=Repository.findAllById(userId);

		if(collab.isPresent())
		{
			return noteRepository.findCollabratorNotes(collab.get()).get();

		}

		return new ArrayList<Note>();

	}





	public List<User> getCollabNote(String token,long noteId)
	{

		long userId=UserToken.tokenVerify(token);
		Note note=noteRepository.findById(noteId).get();
		User user=userRepository.findById(userId).orElseThrow(()->
		new UserException(401,"This Email Id Not Exist"));

		if(user.getId().equals(userId))
		{
			List<User> userinfo=note.getCollabuser().stream().collect(Collectors.toList());
			//user.setCollabnote(collabnote);	
			return userinfo;

		}

		return null;




	}

	@Override
	public List<Note> searchNote(String query,String token) {
		// TODO Auto-generated method stub
		long userId=UserToken.tokenVerify(token);
		List<Note> data=elasticSearch.searchData(query,userId);
		System.out.println("dataata"+data);
		return data;
	}

}






