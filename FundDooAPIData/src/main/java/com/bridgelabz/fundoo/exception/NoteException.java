package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoteException extends RuntimeException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
    private String fieldName;
    private int fieldValue;
    private Long noteId;

//    public NoteException( String resourceName, String fieldName, Object fieldValue) {
//       super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
    
    public NoteException()
    {
    	
    }
    public NoteException(int fieldValue,String resourceName,long noteId) {
    	super(String.format(" %s : '%s'",resourceName,noteId));
		this.fieldValue=fieldValue;
		this.noteId=noteId;
	}

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
