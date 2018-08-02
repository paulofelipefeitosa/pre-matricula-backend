package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student save(Student student) {
        student.setRole(Role.STUDENT);
        return this.studentRepository.save(student);
    }

    public Student getStudentByEnrollmentNumber(String enrollmentNumber) {
        return this.studentRepository.findStudentFromEnrollmentNumber(enrollmentNumber);
    }

    public Student getStudentByEmail(String email) {
        return this.studentRepository.findStudentFromEmail(email);
    }

    public boolean isStudentEmail(String email) {
        boolean result = false;
        if (email.contains("@" + Student.DOMAIN)) {
            result = true;
        }
        return result;
    }
}
