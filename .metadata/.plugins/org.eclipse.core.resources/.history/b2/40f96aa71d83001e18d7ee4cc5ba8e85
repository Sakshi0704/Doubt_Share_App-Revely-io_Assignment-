package com.doubtsharing.models;


import com.doubtsharing.dto.StudentDTO;
import com.doubtsharing.dto.TutorDTO;
import com.doubtsharing.enums.Language;
import com.doubtsharing.enums.Subject;
import com.doubtsharing.enums.UserType;
import com.doubtsharing.enums.UsersGrade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
//@Getter
//@Setter
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;	
	private String userName;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private UserType userType; // tutor // student

	@Enumerated(EnumType.STRING)
	private Language userLanguage; // student and tutor language

	@Enumerated(EnumType.STRING)
	private UsersGrade class_grade; // for student
	
	@Enumerated(EnumType.STRING)
	private UsersGrade min_grade;  // only for tutor
	
	@Enumerated(EnumType.STRING)
	private UsersGrade max_grade;  // only for tutor
	
	@Enumerated(EnumType.STRING)
    private Subject subjectExpertise; // for tutor
	
	@JsonIgnore
	@OneToOne
	private TutorAvailability tutorAvailability;
	
	public Users() {
		super();
	}


	public Users(TutorDTO tutorDTO) {
	    this.userName = tutorDTO.getUserName();
	    this.email = tutorDTO.getEmail();
	    this.password = tutorDTO.getPassword();
	    this.userType = tutorDTO.getUserType();
	    this.userLanguage = tutorDTO.getUserLanguage();
	    this.min_grade = tutorDTO.getMin_grade();
	    this.max_grade = tutorDTO.getMax_grade();
	    this.subjectExpertise = tutorDTO.getSubjectExpertise();
	}

	public Users(StudentDTO studentDTO) {
	    this.userName = studentDTO.getUserName();
	    this.email = studentDTO.getEmail();
	    this.password = studentDTO.getPassword();
	    this.userType = studentDTO.getUserType();
	    this.userLanguage = studentDTO.getUserLanguage();
	    this.class_grade = studentDTO.getClass_grade();
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public Language getUserLanguage() {
		return userLanguage;
	}


	public void setUserLanguage(Language userLanguage) {
		this.userLanguage = userLanguage;
	}


	public UsersGrade getClass_grade() {
		return class_grade;
	}


	public void setClass_grade(UsersGrade class_grade) {
		this.class_grade = class_grade;
	}


	public UsersGrade getMin_grade() {
		return min_grade;
	}


	public void setMin_grade(UsersGrade min_grade) {
		this.min_grade = min_grade;
	}


	public UsersGrade getMax_grade() {
		return max_grade;
	}


	public void setMax_grade(UsersGrade max_grade) {
		this.max_grade = max_grade;
	}


	public Subject getSubjectExpertise() {
		return subjectExpertise;
	}


	public void setSubjectExpertise(Subject subjectExpertise) {
		this.subjectExpertise = subjectExpertise;
	}


	public TutorAvailability getTutorAvailability() {
		return tutorAvailability;
	}


	public void setTutorAvailability(TutorAvailability tutorAvailability) {
		this.tutorAvailability = tutorAvailability;
	}
	
}
