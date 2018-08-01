package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollmentValidator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.PreEnrollmentRepository;

@Service
public class PreEnrollmentService {

	@Autowired
	private PreEnrollmentRepository preEnrollmentRepository;

	@Autowired
	private PreEnrollmentValidator preEnrollmentValidator;
	
	@Autowired
	private UserService userService;

	public List<PreEnrollment> getPreEnrollments() {
		List<PreEnrollment> preEnrollmentList = this.preEnrollmentRepository.findAll();
		for (PreEnrollment preEnrollment : preEnrollmentList) {
			updateStatus(preEnrollment);
		}
		return preEnrollmentList;
	}

	public PreEnrollment getPreEnrollmentByStudentEnrollment(String studentEnrollment) {
		PreEnrollment preEnrollment = this.preEnrollmentRepository
				.findPreEnrollmentByStudentEnrollmentNumber(studentEnrollment);
		if (preEnrollment != null) {
			updateStatus(preEnrollment);
		}
		return preEnrollment;
	}

	public PreEnrollment save(User user, PreEnrollment preEnrollment) {
		updateStatus(preEnrollment);
		Student studentUser = (Student) user;
		studentUser.setPreEnrollment(preEnrollment);
		this.userService.save(studentUser);
		return preEnrollment;
	}
	
	private void updateStatus(PreEnrollment preEnrollment) {
		this.preEnrollmentValidator.checkPreEnrollmentStatus(preEnrollment);
		this.preEnrollmentRepository.save(preEnrollment);
	}


}