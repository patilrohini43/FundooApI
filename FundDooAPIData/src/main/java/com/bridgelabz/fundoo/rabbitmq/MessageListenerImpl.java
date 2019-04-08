package com.bridgelabz.fundoo.rabbitmq;

import java.io.IOException;
import java.util.Date;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.util.EmailUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MessageListenerImpl implements MessageListener{
	
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
      // mailService.send(emailid, subject, url);
  System.out.println("Message Received:"+message);
  System.out.println(new Date());
  
 }

}
