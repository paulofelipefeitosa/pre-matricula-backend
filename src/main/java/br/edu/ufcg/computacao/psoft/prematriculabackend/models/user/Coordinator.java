package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

public class Coordinator extends User {

    public Coordinator(String name, String password, String enrollmentNumber) {
        super(name, password, enrollmentNumber, Role.COORDINATOR);
    }

}
