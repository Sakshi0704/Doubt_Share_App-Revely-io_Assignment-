package com.doubtsharing.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doubtsharing.enums.Language;
import com.doubtsharing.enums.Subject;
import com.doubtsharing.enums.TutorAvailabilityStatus;
import com.doubtsharing.models.TutorAvailability;

public interface TutorAvailabilityRepository extends JpaRepository<TutorAvailability, Integer>{
	
	@Query("SELECT t FROM TutorAvailability t where t.onlineStatus = :onlineStatus")
	public List<TutorAvailability> findByOnlineStatus(@Param("onlineStatus")TutorAvailabilityStatus onlineStatus);
	
	@Query("SELECT t FROM TutorAvailability t JOIN t.user u WHERE u.email = :email")
	public Optional<TutorAvailability> findTutorAvailabilityByEmail(@Param("email") String email);
	
	@Query("SELECT count(t) from TutorAvailability t WHERE t.lastPingTime BETWEEN :startingTime AND :endingTime")
	public int countAvailableTutorsByLastPingTimeUnderThreeSecondFromNow(@Param("startingTime") LocalDateTime startingTime
			                                                          ,@Param("endingTime") LocalDateTime endingTime);
	
//	@Modifying
//	@Query("UPDATE TutorAvailability t SET t.lastPingTime = :lastPingTime WHERE t.onlineStatus = :onlineStatus")
//	public void updateLastPingTimeOfOnlineAvaiableTutor(@Param("onlineStatus") String onlineStatus , 
//																@Param("newTime") LocalDateTime lastPingTime);
	
	@Query("SELECT t FROM TutorAvailability t JOIN t.user u "
			+ "WHERE t.onlineStatus = :onlineStatus "
			+ "AND u.subjectExpertise = :subject "
			+ "AND u.userLanguage = :userLanguage")
	public List<TutorAvailability> findTutorsByOnlineStatusBySubject(@Param("onlineStatus") TutorAvailabilityStatus onlineStatus, 
	                                                               @Param("subject") Subject subject, 
	                                                               @Param("userLanguage") Language userLanguage);
}
