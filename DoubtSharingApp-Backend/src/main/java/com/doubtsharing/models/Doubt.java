package com.doubtsharing.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.doubtsharing.enums.DoubtStatus;
import com.doubtsharing.enums.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Doubt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doubtRequestId;    
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Subject doubtSubject; // 1,2,3...
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String doubtTitle;  // by student
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String doubtDescription;    // by student
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Users student;  // when user is student 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id")
    @JsonIgnore
    private Users tutor; // when user is tutor
    
    @CurrentTimestamp
    private LocalDateTime requestTime;
    
    @Enumerated(EnumType.STRING)
    private DoubtStatus doubtStatus = DoubtStatus.PANDDING; // resolved , padding , assigned
   
    private String doubtResolveDescription;  // by tutor
    
    @JsonProperty(access = Access.READ_ONLY)
    @UpdateTimestamp
    private LocalDateTime reloveTime;  // after resolve the doubt by tutor

	public Integer getDoubtRequestId() {
		return doubtRequestId;
	}

	public void setDoubtRequestId(Integer doubtRequestId) {
		this.doubtRequestId = doubtRequestId;
	}

	public Subject getDoubtSubject() {
		return doubtSubject;
	}

	public void setDoubtSubject(Subject doubtSubject) {
		this.doubtSubject = doubtSubject;
	}

	public String getDoubtTitle() {
		return doubtTitle;
	}

	public void setDoubtTitle(String doubtTitle) {
		this.doubtTitle = doubtTitle;
	}

	public String getDoubtDescription() {
		return doubtDescription;
	}

	public void setDoubtDescription(String doubtDescription) {
		this.doubtDescription = doubtDescription;
	}

	public Users getStudent() {
		return student;
	}

	public void setStudent(Users student) {
		this.student = student;
	}
	
	public Users getTutor() {
		return tutor;
	}

	public void setTutor(Users tutor) {
		this.tutor = tutor;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public DoubtStatus getDoubtStatus() {
		return doubtStatus;
	}

	public void setDoubtStatus(DoubtStatus doubtStatus) {
		this.doubtStatus = doubtStatus;
	}

	public String getDoubtResolveDescription() {
		return doubtResolveDescription;
	}

	public void setDoubtResolveDescription(String doubtResolveDescription) {
		this.doubtResolveDescription = doubtResolveDescription;
	}

	public LocalDateTime getReloveTime() {
		return reloveTime;
	}

	public void setReloveTime(LocalDateTime reloveTime) {
		this.reloveTime = reloveTime;
	}
	
    
    
}
