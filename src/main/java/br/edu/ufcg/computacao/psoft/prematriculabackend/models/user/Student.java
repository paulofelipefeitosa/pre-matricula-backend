package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;

public class Student extends User {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String DOMAIN = "ccc.ufcg.edu.br";
    private String cpf;
    private Date birthdate;
    private String admissionPeriod;
    private Map<String, PreEnrollment> preEnrollments;

    public Student(String email, String name, String enrollmentNumber, String cpf, Date birthdate,
            String admissionPeriod) {
        super(name, email, enrollmentNumber, Role.STUDENT);
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.admissionPeriod = admissionPeriod;
        this.preEnrollments = new HashMap<String, PreEnrollment>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (this.cpf == null || this.cpf.trim().isEmpty()) {
            this.cpf = cpf;
        } else {
            throw new InvalidUpdateException("Cpf is already set");
        }
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAdmissionPeriod() {
        return admissionPeriod;
    }
    
    public void setAdmissionPeriod(String admissionPeriod) {
        if (this.admissionPeriod == null || this.admissionPeriod.trim().isEmpty()) {
            this.admissionPeriod = admissionPeriod;
        } else {
            throw new InvalidUpdateException("Admission period is already set");
        }
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

    @Override
    public String toString() {
        return "Student [cpf=" + cpf + ", birthdate=" + birthdate + ", admissionPeriod="
                + admissionPeriod + ", preEnrollments=" + preEnrollments + ", toString()="
                + super.toString() + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole().toString());
    }

}
