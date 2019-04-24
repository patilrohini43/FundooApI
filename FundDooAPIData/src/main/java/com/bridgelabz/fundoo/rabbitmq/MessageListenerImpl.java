package com.bridgelabz.fundoo.rabbitmq;

import java.io.IOException;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.elasticSearch.ElasticSearch;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MessageListenerImpl implements MessageListener{
	@Autowired
	private ElasticSearch elasticSearch;

	@Override
	public void onMessage(byte[] message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(new Date());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(message);
		String msg = new String(message);
		System.out.println("Message Received:"+msg);
		ObjectMapper objectMapper = new ObjectMapper();
		RabbitMqBody body = objectMapper.readValue(msg, RabbitMqBody.class);
		System.out.println("messsage body--->"+body);
		String toEmail = body.getEmailId();
		String subject = body.getSubject();
		String url = body.getUrl();
		EmailUtil.sendEmail(toEmail,subject,url);

		System.out.println("Message Received:"+msg);
		System.out.println(new Date());

	}

	@Override
	@RabbitListener(queues = "fundoo.note.queue")
	public void operation(NoteContainer notecontainer) {
		System.out.println("hiiiiiii");
		
		
		Note note=notecontainer.getNote();
		switch(notecontainer.getNoteoperation())
		{
		case CREATE : try {

			elasticSearch.save(note);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;

		case UPDATE :elasticSearch.updateNote(note);
		break;

		case DELETE :elasticSearch.deleteNote(note.getNoteId());
		break;
		}	
	}
}
