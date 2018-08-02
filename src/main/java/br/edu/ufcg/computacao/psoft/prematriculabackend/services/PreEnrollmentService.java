package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollmentValidator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.Status;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.PreEnrollmentRepository;

@Service
public class PreEnrollmentService {

    @Autowired
    private PreEnrollmentRepository preEnrollmentRepository;

    @Autowired
    private PreEnrollmentValidator preEnrollmentValidator;

    @Autowired
    private StudentService studentService;

    public List<PreEnrollment> getPreEnrollments() {
        return this.preEnrollmentRepository.findAll();
    }

    public PreEnrollment getPreEnrollmentByStudentEnrollment(String studentEnrollment) {
        return this.preEnrollmentRepository
                .findPreEnrollmentByStudentEnrollmentNumber(studentEnrollment);
    }

    public PreEnrollment save(Student student, PreEnrollment preEnrollment) {
        Status status = this.preEnrollmentValidator.getPreEnrollmentStatus(preEnrollment);
        preEnrollment.setStatus(status);
        student.setPreEnrollment(preEnrollment);
        this.studentService.save(student);
        return this.preEnrollmentRepository.save(preEnrollment);
    }

}
