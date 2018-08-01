
package br.edu.ufcg.computacao.psoft.prematriculabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;

public interface PreEnrollmentRepository  extends JpaRepository<PreEnrollment, String> {
	
	@Query("select u from PreEnrollment u where u.studentEnrollment = ?1")
	public PreEnrollment findPreEnrollmentByStudentEnrollmentNumber(String studentEnrollment);

}