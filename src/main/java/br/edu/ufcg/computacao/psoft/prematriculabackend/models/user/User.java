package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Anonymous.class, name = "ANONYMOUS"),
		@JsonSubTypes.Type(value = Student.class, name = "STUDENT"),
		@JsonSubTypes.Type(value = Coordinator.class, name = "COORDINATOR")})
public abstract class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "enrollmentNumber")
	@NotNull(message = "User enrollmentNumber can not be null")
	@NotEmpty(message = "User enrollmentNumber can not be empty")
	private String enrollmentNumber;

	@Column(name = "email")
	@NotNull(message = "User email can not be null")
	@NotEmpty(message = "User email can not be empty")
	private String email;

	@Column(name = "username")
	@NotNull(message = "User username can not be null")
	@NotEmpty(message = "User username can not be empty")
	private String username;

	@Column(name = "role")
	@NotNull(message = "User role can not be null")
	private Role role;

	public User() {

	}

	public User(String name, String email, String enrollmentNumber, Role role) {
		this.email = email;
		this.username = name;
		this.enrollmentNumber = enrollmentNumber;
		this.role = role;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		if (this.enrollmentNumber == null || this.enrollmentNumber.trim().isEmpty()) {
			this.enrollmentNumber = enrollmentNumber;
		} else {
			throw new InvalidUpdateException("Enrollment number is already set");
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", userName=" + username + ", enrollmentNumber=" + enrollmentNumber + ", role="
				+ role + "]";
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
	
	
}
