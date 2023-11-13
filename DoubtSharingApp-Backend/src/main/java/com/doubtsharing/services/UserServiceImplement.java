package com.doubtsharing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubtsharing.exception.DuplicateDataException;
import com.doubtsharing.exception.InvalidArgumentException;
import com.doubtsharing.exception.RecordNotFoundException;
import com.doubtsharing.models.Users;
import com.doubtsharing.repository.UsersRepository;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public Users registerUser(Users user) throws InvalidArgumentException, DuplicateDataException {
		
		if(user == null) {
			throw new InvalidArgumentException("Invalid user details provided Or user can not be null");
		}
		
		Optional<Users> userOpt = userRepository.findByEmail(user.getEmail());
		
		if(userOpt.isPresent()) {
			throw new DuplicateDataException("Already exit user with email "+user.getEmail());
		}
		
		Users savedUser = userRepository.save(user);
		
		return savedUser;
	}

	@Override
	public Users retriveSpecificUser(String email) throws InvalidArgumentException, RecordNotFoundException {
		
		if(email==null || email.isBlank() || email.isEmpty())
			throw new InvalidArgumentException("Invalid email provided Or email should not null or blank or empty");
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isEmpty()) {
			throw new RecordNotFoundException("With email "+email+" User not exists");
		}
		
		return userOpt.get();
	}

	@Override
	public List<Users> retriveAllUsers() throws RecordNotFoundException {
		
		List<Users> allusers = userRepository.findAll();
		if(allusers.isEmpty()) {
			throw new RecordNotFoundException("No User Found In Record");
		}
		
		return allusers;
	}
	
	

}
