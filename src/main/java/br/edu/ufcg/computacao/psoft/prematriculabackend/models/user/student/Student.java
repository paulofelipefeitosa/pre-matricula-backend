package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.student;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

public class Student extends User {
    private String cpf;
    private Date birthdate;
    private String admissionPeriod;
    private Set<PreEnrollment> preEnrollments;

    public Student(String name, String password, String enrollmentNumber, String cpf,
            Date birthdate, String admissionPeriod) {
        super(name, password, enrollmentNumber, Role.STUDENT);
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.admissionPeriod = admissionPeriod;
        this.preEnrollments = new HashSet<PreEnrollment>();
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

    public Set<PreEnrollment> getPreEnrollments() {
        return preEnrollments;
    }

}
