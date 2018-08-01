package br.edu.ufcg.computacao.psoft.prematriculabackend.googleauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@Component
public class GooglePrincipalExtractor implements PrincipalExtractor {

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoTranslator googleUserInfo;

	@Autowired
	private Coordinator coordinator;

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		String email = this.googleUserInfo.getEmail(map);
		User user = this.userService.getUserByEmail(email);
		if (user == null) {
			String userName = this.googleUserInfo.getName(map);
			if (email.equals(this.coordinator.getEmail())) {
				user = coordinator;
				user.setUsername(userName);
				this.userService.save(user);
			} else if (email.contains(Student.DOMAIN)) {
				user = new Student(userName, email, null, null, null, null);
				this.userService.save(user);
			}
		}
		return user;
	}

}
