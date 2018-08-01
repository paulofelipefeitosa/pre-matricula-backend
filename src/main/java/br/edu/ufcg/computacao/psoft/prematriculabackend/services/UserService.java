package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	public User save(User user) {
		log.info("Antes de salvar!!");
		this.userRepository.save(user);
		log.info("salvo!!");
		return user;
	}

	public User getUserByEmail(String email) {
		return this.userRepository.findUserFromEmail(email);
	}

}
