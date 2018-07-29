package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.student;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

public class Student extends User {
    private String cpf;
    private Date birthdate;
    private String admissionPeriod;
    private Map<String, PreEnrollment> preEnrollments;

    public Student(String email, String name, String enrollmentNumber, String cpf,
            Date birthdate, String admissionPeriod) {
        super(email, name, enrollmentNumber, Role.STUDENT);
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.admissionPeriod = admissionPeriod;
        this.preEnrollments = new HashMap<String, PreEnrollment>();
    }

    public String getCpf() {
        return cpf;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAdmissionPeriod() {
        return admissionPeriod;
    }
    
    public void addPreEnrollment(String semester, PreEnrollment preEnrollment) {
        this.preEnrollments.put(semester, preEnrollment);
    }

    public PreEnrollment getPreEnrollmentBySemester(String semester) {
        return this.preEnrollments.get(semester);
    }
    
    public Collection<PreEnrollment> getAllPreEnrollments() {
        return this.preEnrollments.values();
    }
}
