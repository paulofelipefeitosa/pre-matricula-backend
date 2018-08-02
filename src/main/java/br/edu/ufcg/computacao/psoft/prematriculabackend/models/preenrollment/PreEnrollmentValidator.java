package br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment;

import org.springframework.stereotype.Component;

@Component
public interface PreEnrollmentValidator {

	public Status getPreEnrollmentStatus(PreEnrollment preEnrollment);
}
