package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

@Service
public class AuthenticationService {

	@Autowired
	private Coordinator coordinator;

	@Autowired
	private UserService userService;

	private Map<String, User> tokenMapper = new HashMap<String, User>();

	public void createToken(String token, User user) {
		if (isStudent(user)) {
			User persistedUser = this.userService.getUserByEmail(user.getEmail());
			if (persistedUser != null) {
				user = persistedUser;
			} else {
				this.userService.save(user);
			}
		} else if (isCoordinator(user)) {
			this.coordinator.setUsername(user.getUsername());
			user = this.coordinator;
		} else {
			throw new UnauthorizedException("User does not belong to CCC organization");
		}
		this.tokenMapper.put(token, user);
	}

	public User getUser(String token) {
		if (this.tokenMapper.containsKey(token)) {
			return this.tokenMapper.get(token);
		}
		throw new UnauthorizedException("User not authenticated");
	}

	private boolean isStudent(User user) {
		boolean result = false;
		if (user.getEmail().contains("@" + Student.DOMAIN)) {
			result = true;
		}
		return result;
	}

	private boolean isCoordinator(User user) {
		boolean result = false;
		if (user.getEmail().equals(this.coordinator.getEmail())) {
			result = true;
		}
		return result;
	}

}
