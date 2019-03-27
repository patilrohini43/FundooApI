package com.bridgelabz.fundoo.label.service;

import java.util.List;

import org.springframework.validation.Errors;

import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.model.LabelDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;

public interface LabelService {
	
	public Response createLabel(long noteId,LabelDto labelDto,String token);
	public Response updateLabel(long labelId,LabelDto labelDto,String token);
	public Response deleteLabel(long labelId,String token);
	public List<Label> getAllLabels(String token);
	public Response addLabel(LabelDto labelDto,String token);

	public List<Note> labelNote(String token,long labelId);

}
