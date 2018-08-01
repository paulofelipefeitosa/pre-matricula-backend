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
		return this.preEnrollmentRepository.findAll();
	}

	public PreEnrollment getPreEnrollmentByStudentEnrollment(String studentEnrollment) {
		return this.preEnrollmentRepository.findPreEnrollmentByStudentEnrollmentNumber(studentEnrollment);
	}

	public PreEnrollment save(PreEnrollment preEnrollment) {
		this.preEnrollmentRepository.save(preEnrollment);
		return preEnrollment;
	}

}