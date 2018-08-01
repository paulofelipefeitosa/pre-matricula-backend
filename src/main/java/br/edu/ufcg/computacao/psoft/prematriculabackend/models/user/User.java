package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;

@Entity
@Table(name = "tb_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonSubTypes({ @JsonSubTypes.Type(value = Student.class, name = "STUDENT"),
		@JsonSubTypes.Type(value = Coordinator.class, name = "COORDINATOR") })
public abstract class User {

	@Column(name = "enrollmentNumber")
	private String enrollmentNumber;

	@Id
	@Column(name = "email")
	@NotNull(message = "User email can not be null")
	@NotEmpty(message = "User email can not be empty")
	private String email;

	@Column(name = "username")
	@NotNull(message = "User username can not be null")
	@NotEmpty(message = "User username can not be empty")
	private String username;

	@Column(name = "role")
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
	public String toString() {
		return "User [email=" + email + ", userName=" + username + ", enrollmentNumber=" + enrollmentNumber + ", role="
				+ role + "]";
	}

}
