package com.doubtsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doubtsharing.models.Doubt;
import com.doubtsharing.services.DoubtHandlingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doubt-sharing-app")
public class DoubtHandlerController {

	@Autowired
	private DoubtHandlingService doubtHandlingService;
	
	@PostMapping("/student/add-doubt-request") // only by student 
	public ResponseEntity<Doubt> addDoubtRequestHandler(@Valid @RequestBody Doubt doubt){
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       
		String email = authentication.getName();
		
		Doubt addedDoubt = doubtHandlingService.addDoubtRequest(doubt, email);
		
		return new ResponseEntity<Doubt>(addedDoubt,HttpStatus.ACCEPTED);
	}
	
	
}
