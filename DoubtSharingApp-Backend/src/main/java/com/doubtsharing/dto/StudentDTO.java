package com.doubtsharing.dto;

import com.doubtsharing.enums.Language;
import com.doubtsharing.enums.Subject;
import com.doubtsharing.enums.UserType;
import com.doubtsharing.enums.UsersGrade;
import com.doubtsharing.models.TutorAvailability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StudentDTO {

	@NotNull(message = "User Name should not be null")
	@NotEmpty(message = "User Name should not be empty")
	@NotBlank(message = "User Name should not be blank")
	private String userName;

	@NotEmpty(message = "Email should not be empty")
	@NotBlank(message = "Email should not be blank")
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private UserType userType = UserType.ROLE_STUDENT; // student

	
	@NotNull(message = "User Language must not null")
	@Enumerated(EnumType.STRING)
	private Language userLanguage; // student and tutor language

	
	@NotNull(message = "Grade must not null")
	@Enumerated(EnumType.STRING)
	private UsersGrade class_grade;  // in which student currently


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
	
}
