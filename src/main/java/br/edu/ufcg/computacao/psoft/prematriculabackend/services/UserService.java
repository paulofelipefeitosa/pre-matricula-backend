package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	public User save(User user) {
		this.userRepository.save(user);
		return user;
	}

	public User getUserByEnrollmentNumber(String enrollmentNumber) {
		return this.userRepository.findUserFromEnrollmentNumber(enrollmentNumber);
	}
	
	public User getUserByEmail(String email) {
		return this.userRepository.findUserFromEmail(email);
	}
	
	
}
