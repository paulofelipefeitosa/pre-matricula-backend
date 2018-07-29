package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.coordinator;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

public class Coordinator extends User {

    public Coordinator(String email, String name, String enrollmentNumber) {
        super(email, name, enrollmentNumber, Role.COORDINATOR);
    }

}
