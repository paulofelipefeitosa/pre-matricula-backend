package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

public abstract class User {
    private String email;
    private String name;
    private String enrollmentNumber;
    private Role role;
    
    public User(String name, String email, String enrollmentNumber, Role role) {
        this.email = email;
        this.name = name;
        this.enrollmentNumber = enrollmentNumber;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

}
