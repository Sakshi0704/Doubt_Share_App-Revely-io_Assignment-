package com.doubtsharing.dto;

import com.doubtsharing.enums.Language;
import com.doubtsharing.enums.Subject;
import com.doubtsharing.enums.UserType;
import com.doubtsharing.enums.UsersGrade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TutorDTO {
	
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
	private UserType userType = UserType.ROLE_TUTOR; // tutor

	//@NotNull(message = "User Language must not null")
	@Enumerated(EnumType.STRING)
	private Language userLanguage; // student and tutor language

	//@NotNull(message = "Min Class Grade must not null")
	@Enumerated(EnumType.STRING)
	private UsersGrade min_grade; // for tutor
	
	//@NotNull(message = "Max Class Grade must not null")
	@Enumerated(EnumType.STRING)
	private UsersGrade max_grade;  // for tutor
		
	@Enumerated(EnumType.STRING)
    private Subject subjectExpertise; // for tutor

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
}
