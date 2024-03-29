package com.nagarro.nottakingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExist extends RuntimeException
{
	private String exceptionDetails;
	private Object fieldValue;
	
	public UserAlreadyExist(String exceptionDetail, Long fieldValue)
	{
		super(exceptionDetail + " - " + fieldValue);
		this.exceptionDetails = exceptionDetail;
		this.fieldValue = fieldValue;
	}
	
	public UserAlreadyExist(String exceptionDetail, String fieldValue)
	{
		super(exceptionDetail + " - " + fieldValue);
		this.exceptionDetails = exceptionDetail;
		this.fieldValue = fieldValue;
	}
	
	public String getExceptionDetails()
	{
		return exceptionDetails;
	}
	
	public Object getFeildValue()
	{
		return fieldValue;
	}

}
