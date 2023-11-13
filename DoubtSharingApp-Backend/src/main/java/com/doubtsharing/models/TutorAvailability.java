package com.doubtsharing.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.doubtsharing.enums.TutorAvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TutorAvailability {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorAvailabilityId;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Users user;
    
    @JsonProperty(access = Access.READ_ONLY)
    @UpdateTimestamp
    private LocalDateTime lastPingTime;

    @Enumerated(EnumType.STRING)
    private TutorAvailabilityStatus onlineStatus;

	public Integer getTutorAvailabilityId() {
		return tutorAvailabilityId;
	}

	public void setTutorAvailabilityId(Integer tutorAvailabilityId) {
		this.tutorAvailabilityId = tutorAvailabilityId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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
