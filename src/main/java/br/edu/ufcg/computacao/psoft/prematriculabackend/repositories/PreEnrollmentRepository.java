package br.edu.ufcg.computacao.psoft.prematriculabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;

public interface PreEnrollmentRepository  extends JpaRepository<PreEnrollment, Long> {

}
