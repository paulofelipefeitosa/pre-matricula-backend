package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;

@Entity
@DiscriminatorValue(value = "tb_student")
public class Student extends User {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String DOMAIN = "ccc.ufcg.edu.br";
	
	@Column(name = "cpf")
    private String cpf;

	@Column(name = "birthdate")
    private Date birthdate;
	
	@Column(name = "admissionPeriod")
    private String admissionPeriod;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_enrollment_id")
    private PreEnrollment preEnrollments;

	public Student() {}
	
    public Student(String name, String email, String enrollmentNumber, String cpf, Date birthdate,
            String admissionPeriod) {
        super(name, email, enrollmentNumber, Role.STUDENT);
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.admissionPeriod = admissionPeriod;
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

    public void updatePreEnrollment(PreEnrollment preEnrollment) {
        this.preEnrollments = preEnrollment;
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
