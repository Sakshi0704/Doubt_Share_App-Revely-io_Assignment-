package com.doubtsharing.exception;

public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException() {
		
	}
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
}
