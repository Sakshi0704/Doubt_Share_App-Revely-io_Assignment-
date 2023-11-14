package com.doubtsharing.services;

import java.util.List;

import com.doubtsharing.exception.InvalidArgumentException;
import com.doubtsharing.exception.RecordNotFoundException;
import com.doubtsharing.models.TutorAvailability;

public interface TutorAvailabilityService {

	public int countOnlineTutors()throws RecordNotFoundException;
	
	public List<TutorAvailability> allavailableTutors() throws RecordNotFoundException;
	
	public TutorAvailability updateTutorAvailabilityAsAvailable(String email) throws InvalidArgumentException;
	
	public TutorAvailability updateTutorAvailabilityAsNotAvailable(String email) throws InvalidArgumentException;
	
}
