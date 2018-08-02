package br.edu.ufcg.computacao.psoft.prematriculabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, String> {

    @Query("select u from Coordinator u where u.enrollmentNumber = ?1")
    public Coordinator findCoordinatorFromEnrollmentNumber(String enrollmentNumber);

    @Query("select u from Coordinator u where u.email = ?1")
    public Coordinator findCoordinatorFromEmail(String email);

}
