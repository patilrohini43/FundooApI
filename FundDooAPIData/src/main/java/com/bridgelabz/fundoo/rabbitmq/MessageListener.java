package com.bridgelabz.fundoo.rabbitmq;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MessageListener {
	 
	 public void onMessage(byte[] message) throws JsonParseException, JsonMappingException, IOException;
	// public void operation(NoteContainer notecontainer);
//	void operation(byte[] message);
	public void operation(NoteContainer notecontainer);
	}