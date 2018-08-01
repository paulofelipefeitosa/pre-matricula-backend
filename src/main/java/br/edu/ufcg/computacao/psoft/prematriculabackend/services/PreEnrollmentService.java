package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.PreEnrollmentRepository;

@Service
public class PreEnrollmentService {
	@Autowired
	PreEnrollmentRepository preEnrollmentRepository;

	public List<PreEnrollment> getPreEnrollments() {
		List<PreEnrollment> preEnrollmentList = this.preEnrollmentRepository.findAll();
		for (PreEnrollment preEnrollment : preEnrollmentList) {
			preEnrollment.updateStatus();
			this.save(preEnrollment);
		}
		return preEnrollmentList;
	}

	public PreEnrollment getPreEnrollmentByStudentEnrollment(String studentEnrollment) {
		PreEnrollment preEnrollment = this.preEnrollmentRepository
				.findPreEnrollmentByStudentEnrollmentNumber(studentEnrollment);
		if (preEnrollment != null) {
			preEnrollment.updateStatus();
			this.save(preEnrollment);
		}
		return preEnrollment;
	}

	public PreEnrollment save(PreEnrollment preEnrollment) {
		this.preEnrollmentRepository.save(preEnrollment);
		return preEnrollment;
	}

}