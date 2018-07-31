package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import org.springframework.security.core.userdetails.UserDetails;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.InvalidUpdateException;

public abstract class User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String email;
    private String username;
    private String enrollmentNumber;
    private Role role;

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
        return "User [email=" + email + ", userName=" + username + ", enrollmentNumber="
                + enrollmentNumber + ", role=" + role + "]";
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

}
