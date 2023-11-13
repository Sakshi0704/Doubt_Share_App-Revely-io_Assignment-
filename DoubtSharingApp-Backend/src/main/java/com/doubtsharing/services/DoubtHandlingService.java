package com.doubtsharing.services;

import java.util.List;
import java.util.Optional;

import com.doubtsharing.exception.InvalidArgumentException;
import com.doubtsharing.exception.RecordNotFoundException;
import com.doubtsharing.models.Doubt;

public interface DoubtHandlingService {
	
	public Doubt addDoubtRequest(Doubt doubt,String email) 
			throws InvalidArgumentException;   // by student only
	
	public Doubt tutorAvailableLiveToResolveDoubt(Doubt doubtRequest, String email) 
			throws InvalidArgumentException;  // by student only
	
	public Doubt doubtResolveByTutor(Integer doubtId, String solvedDesc, String email) 
				throws InvalidArgumentException;
	
	public List<Doubt> paddingDoubtsForSpecificTutorByRequestTimeDesc(String email) 
				throws RecordNotFoundException,InvalidArgumentException;
	
	public List<Doubt> getDoubtRequestHistoryByEmailByRequestTimeDesc(String email) 
			throws InvalidArgumentException,RecordNotFoundException;  // by student only
}
