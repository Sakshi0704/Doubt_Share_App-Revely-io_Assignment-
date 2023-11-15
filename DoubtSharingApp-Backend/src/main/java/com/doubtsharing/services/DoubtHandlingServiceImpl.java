package com.doubtsharing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubtsharing.exception.InvalidArgumentException;
import com.doubtsharing.exception.RecordNotFoundException;
import com.doubtsharing.models.Doubt;
import com.doubtsharing.models.TutorAvailability;
import com.doubtsharing.dto.StudentDTO;
import com.doubtsharing.enums.DoubtStatus;
import com.doubtsharing.enums.TutorAvailabilityStatus;
import com.doubtsharing.models.Users;
import com.doubtsharing.repository.DoubtRepository;
import com.doubtsharing.repository.TutorAvailabilityRepository;
import com.doubtsharing.repository.UsersRepository;

@Service
public class DoubtHandlingServiceImpl implements DoubtHandlingService {

	@Autowired
	private DoubtRepository doubtRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TutorAvailabilityRepository tutorAvailableRepository;

	@Override
	public Doubt addDoubtRequest(Doubt doubtRequest, String email)
			throws InvalidArgumentException, RecordNotFoundException {

		if (email == null || email.isBlank() || email.isEmpty()) {
			throw new InvalidArgumentException("Student email " + email + " is invalid");
		}

		if (doubtRequest == null) {
			throw new InvalidArgumentException("doubt request is invalid");
		}
		Optional<Users> user = usersRepository.findByEmail(email);

		if (user.isEmpty()) {
			throw new RecordNotFoundException("No Student found with email " + email);
		}

		doubtRequest.setStudent(user.get());

		return doubtRepository.save(doubtRequest);
	}

	@Override
	public Doubt tutorAvailableLiveToResolveDoubt(Integer doubtId, String email) throws InvalidArgumentException {
		if(doubtId<=0) {
			throw new InvalidArgumentException("Invalid argument is assigned");
		}
		
		Optional<Doubt> doubtRequestOpt = doubtRepository.findById(doubtId);
		
		if (doubtRequestOpt.isEmpty()) {
			throw new InvalidArgumentException("Invalid doubt request.");
		}

		Doubt doubtRequest = doubtRequestOpt.get();
		
		if (!doubtRequest.getDoubtStatus().equals(DoubtStatus.PENDING)) {
			throw new InvalidArgumentException("Doubt request is not in the PENDING status.");
		}
		
		Users student = doubtRequest.getStudent();

		List<TutorAvailability> availableTutors = tutorAvailableRepository.findTutorsByOnlineStatusBySubject(
				TutorAvailabilityStatus.AVAILABLE, doubtRequest.getDoubtSubject(),
				student.getUserLanguage());
		// Iterate through available tutors to find a suitable match......
		for (TutorAvailability tutorAvailability : availableTutors) {

			Users tutor = tutorAvailability.getUser();

			// Check if the student's class/grade falls within the range the tutor teaches.
			int studentGrade = selectGrades(student.getClass_grade().toString());
			int tutorMinGrade = selectGrades(tutor.getMin_grade().toString());
			int tutorMaxGrade = selectGrades(tutor.getMax_grade().toString());
			if (isStudentGradeInRange(studentGrade, tutorMinGrade,tutorMaxGrade)) {

				// Assign the tutor to the doubt request.
				doubtRequest.setTutor(tutor);
				doubtRequest.setDoubtStatus(DoubtStatus.ASSIGNED);

				// Save the updated doubt using your DoubtRepository.
				Doubt updatedDoubt = doubtRepository.save(doubtRequest);

				return updatedDoubt;
			}
		}
		throw new InvalidArgumentException("No available tutors match the criteria to resolve this doubt.");
	}

	private boolean isStudentGradeInRange(int studentGrade, int min_grade, int max_grade) {

		if (studentGrade >=  min_grade && studentGrade <= max_grade) {
			return true;
		}

		return false;
	}

	@Override
	public List<Doubt> panddingDoubtsForSpecificTutorByRequestTimeDesc(String email)
			throws RecordNotFoundException, InvalidArgumentException { // by tutor
		// TODO Auto-generated method stub

		if (email == null || email.isBlank() || email.isEmpty())
			throw new InvalidArgumentException("Invalid email is given of tutor");

		List<Doubt> listOfDoubts = doubtRepository.findDoubtsByDoubtsStatusByTutorByRequestTimeDesc(email,
				DoubtStatus.ASSIGNED);

		if (listOfDoubts.isEmpty()) {
			throw new RecordNotFoundException("For Tutor " + email + " no padding doubt left");
		}

		return listOfDoubts;
	}

	@Override
	public Doubt doubtResolveByTutor(Integer doubtId, String solvedDesc, String email)
			throws InvalidArgumentException, RecordNotFoundException {

		if (doubtId <= 0 || solvedDesc == null || solvedDesc.isEmpty() || solvedDesc.isBlank()) {
			throw new InvalidArgumentException("Invalid arguments are provided !!");
		}

		Optional<Users> tutorOpt = usersRepository.findByEmail(email);
		if (tutorOpt.isEmpty()) {
			throw new RecordNotFoundException("No Tutor found by email " + email);
		}

		Optional<Doubt> doubtOpt = doubtRepository.findById(doubtId);
		if (doubtOpt.isEmpty()) {
			throw new RecordNotFoundException("No such doubt found with doubtId " + doubtId);
		}

		Doubt doubt = doubtOpt.get();
		if (doubt.getDoubtStatus().toString().equals(DoubtStatus.RESOLVED.toString())){
			throw new RecordNotFoundException("Doubt is solved already by tutor " + doubt.getTutor().getUserName());
		}
		doubt.setTutor(tutorOpt.get());
		doubt.setDoubtResolveDescription(solvedDesc);
		doubt.setDoubtStatus(DoubtStatus.RESOLVED);
	

		return doubtRepository.save(doubt);
	}

	@Override
	public List<Doubt> getDoubtRequestHistoryByEmailByRequestTimeDesc(String email)
			throws InvalidArgumentException, RecordNotFoundException {

		if (email == null || email.isBlank() || email.isEmpty())
			throw new InvalidArgumentException("Invalid email is given of student");

		List<Doubt> listOfDoubts = doubtRepository.findDoubtsByStudentByRequestTimeDesc(email);

		if (listOfDoubts.isEmpty()) {
			throw new RecordNotFoundException("No Doubts On Record for Student " + email);
		}

		return listOfDoubts;

	}

	private int selectGrades(String grade) {

		switch (grade) {

		case "Grade_1":
			return 1;
		case "Grade_2":
			return 2;
		case "Grade_3":
			return 3;
		case "Grade_4":
			return 4;
		case "Grade_5":
			return 5;
		case "Grade_6":
			return 6;
		case "Grade_7":
			return 7;
		case "Grade_8":
			return 8;
		case "Grade_9":
			return 9;
		case "Grade_10":
			return 10;
		case "Grade_11":
			return 11;
		case "Grade_12":
			return 12;
		default:
			return -1;
		}

	}

}
