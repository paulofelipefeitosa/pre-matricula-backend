package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.PreEnrollmentRepository;

public class PreEnrollmentService {
	@Autowired
	PreEnrollmentRepository preEnrollmentRepository;

	public List<PreEnrollment> getUsers() {
		return this.preEnrollmentRepository.findAll();
	}
	
	public PreEnrollment save(PreEnrollment preEnfolmetEnrollment) {
		this.preEnrollmentRepository.save(preEnfolmetEnrollment);
		return preEnfolmetEnrollment;
	}

}
