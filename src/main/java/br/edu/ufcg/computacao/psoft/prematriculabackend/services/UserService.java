package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEnrollmentNumber(String enrollmentNumber) {
        return this.userRepository.findUserFromEnrollmentNumber(enrollmentNumber);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findUserFromEmail(email);
    }


}
