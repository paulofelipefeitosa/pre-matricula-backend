package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Date;

public class Student extends User {
    private String cpf;
    private Date birthdate;
    private String admissionPeriod;

    public Student(String name, String password, String enrollmentNumber, String cpf,
            Date birthdate, String admissionPeriod) {
        super(name, password, enrollmentNumber, Role.STUDENT);
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.admissionPeriod = admissionPeriod;
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
    
}
