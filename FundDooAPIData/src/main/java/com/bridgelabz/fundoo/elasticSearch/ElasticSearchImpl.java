package com.bridgelabz.fundoo.elasticSearch;


import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class ElasticSearchImpl implements ElasticSearch{

	 private final String INDEX = "notedata";
	  private final String TYPE = "note";  
	 
	  @Autowired
	 private RestHighLevelClient client;
	  
	  @Autowired
		private Environment environment;
	  
	  final ObjectMapper mapper = new ObjectMapper(); 

	
	@Override
	public Note save(Note note) throws IOException {
		// TODO Auto-generated method stub
	
		@SuppressWarnings("unchecked")
		//convert our Note Object to a Map object
		 Map<String, String> dataMap = mapper.convertValue(note, Map.class);
		
		System.out.println(dataMap);
		IndexRequest indexRequest = new IndexRequest(INDEX,TYPE,String.valueOf(note.getNoteId()))
                .source(dataMap);
		 client.index(indexRequest, RequestOptions.DEFAULT);
		 
  return note;
			}
	
	
	@Override
	public Note updateNote(Note note) 
	{
		System.out.println("hello");
		  UpdateRequest updateRequest = new UpdateRequest(
	                INDEX,
	                TYPE,
	String.valueOf(note.getNoteId()));
		  
		  

	        @SuppressWarnings("unchecked")
			Map<String, Object> documentMapper = mapper.convertValue(note, Map.class);

	        updateRequest.doc(documentMapper);

	        try {
				@SuppressWarnings("unused")
				UpdateResponse updateResponse = 
				  client.update(updateRequest, RequestOptions.DEFAULT);
				System.out.println("Update SuccessFully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return note;
	}
	

	
	
	public void deleteNote(Long NoteId) {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(NoteId));
        try {
			DeleteResponse response =
			        client.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		


}
	
	
	
	
	
	
	
	

}
