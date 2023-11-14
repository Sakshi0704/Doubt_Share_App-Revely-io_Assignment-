package com.doubtsharing.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.doubtsharing.enums.TutorAvailabilityStatus;
import com.doubtsharing.models.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TutorAvailabilityDTO {

    private Integer tutorAvailabilityId;
	
    private String tutorName;
    
    private LocalDateTime lastPingTime;

    private TutorAvailabilityStatus onlineStatus;
    
	public TutorAvailabilityDTO() {
		super();
	}

	public TutorAvailabilityDTO(Integer tutorAvailabilityId, Users tutor, LocalDateTime lastPingTime,
			TutorAvailabilityStatus onlineStatus) {
		super();
		this.tutorAvailabilityId = tutorAvailabilityId;
		if(tutor != null)
		this.tutorName = tutor.getUserName();
		this.lastPingTime = lastPingTime;
		this.onlineStatus = onlineStatus;
	}

	public Integer getTutorAvailabilityId() {
		return tutorAvailabilityId;
	}

	public void setTutorAvailabilityId(Integer tutorAvailabilityId) {
		this.tutorAvailabilityId = tutorAvailabilityId;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public LocalDateTime getLastPingTime() {
		return lastPingTime;
	}

	public void setLastPingTime(LocalDateTime lastPingTime) {
		this.lastPingTime = lastPingTime;
	}

	public TutorAvailabilityStatus getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(TutorAvailabilityStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
   
	
}
