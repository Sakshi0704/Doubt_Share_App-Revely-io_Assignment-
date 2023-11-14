package com.doubtsharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doubtsharing.models.TutorAvailability;
import com.doubtsharing.services.TutorAvailabilityService;

@RestController
@RequestMapping("/doubt-sharing-app")
public class TutorHandlerController {

	@Autowired
	private TutorAvailabilityService tutorAvailabilityService; 
	
	@GetMapping("tutors/count-online/available-tutors")
	public ResponseEntity<Integer> countOnlineTutorsHandler() {  // only by tutors
		return new ResponseEntity<>(tutorAvailabilityService.countOnlineTutors(),HttpStatus.OK);
	}
	
	@GetMapping("/tutors/all-available")
	public ResponseEntity<List<TutorAvailability>> availableTutorsHandler(){ // only by tutors
		 return new ResponseEntity<>(tutorAvailabilityService.allavailableTutors(),HttpStatus.OK);
	}
}
