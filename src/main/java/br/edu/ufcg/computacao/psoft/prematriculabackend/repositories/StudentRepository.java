package br.edu.ufcg.computacao.psoft.prematriculabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    
    @Query("select u from Student u where u.enrollmentNumber = ?1")
    public Student findStudentFromEnrollmentNumber(String enrollmentNumber);

    @Query("select u from Student u where u.email = ?1")
    public Student findStudentFromEmail(String email);
}
