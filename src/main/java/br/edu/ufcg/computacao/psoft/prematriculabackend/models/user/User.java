package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

public abstract class User {
    private String name;
    private String password;
    private String enrollmentNumber;
    private Role role;
    
    public User(String name, String password, String enrollmentNumber, Role role) {
        this.name = name;
        this.password = password;
        this.enrollmentNumber = enrollmentNumber;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public Role getRole() {
        return role;
    }
    
}
