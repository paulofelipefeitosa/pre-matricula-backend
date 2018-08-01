package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;

@Entity
@DiscriminatorValue(value = "student")
public class Student extends User {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String DOMAIN = "ccc.ufcg.edu.br";
	
	@Column(name = "cpf")
    @NotNull(message = "User cpf can not be null")
	@NotEmpty(message = "User cpf can not be empty")
    private String cpf;

	@Column(name = "birthdate")
    @NotNull(message = "User birthdate can not be null")
	@NotEmpty(message = "User birthdate can not be empty")
    private Date birthdate;
	
	@Column(name = "admissionPeriod")
    @NotNull(message = "User admissionPeriod can not be null")
	@NotEmpty(message = "User admissionPeriod can not be empty")
    private String admissionPeriod;
	
	@ManyToMany
	@JoinColumn(name = "preEnrollments")
    @NotNull(message = "User preEnrollments can not be null")
	@NotEmpty(message = "User preEnrollments can not be empty")
    private Map<String, PreEnrollment> preEnrollments;

	public Student() {
        this.preEnrollments = new HashMap<String, PreEnrollment>();
	}
	
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

	public Map<String, PreEnrollment> getPreEnrollments() {
		return preEnrollments;
	}

	public void setPreEnrollments(Map<String, PreEnrollment> preEnrollments) {
		this.preEnrollments = preEnrollments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getDomain() {
		return DOMAIN;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
    
    

}
