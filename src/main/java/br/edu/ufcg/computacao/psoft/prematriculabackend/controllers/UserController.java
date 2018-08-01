package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody User getUser(
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		return this.authService.getUser(tokenValue);
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public @ResponseBody Student createStudent(@RequestBody Student user,
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		logger.info(user.toString());
		User authUser = this.userService.getUserByEmail(user.getEmail());
		if (authUser != null && authUser.getEmail().equals(user.getEmail())) {
			logger.info(authUser.toString());
			this.authService.createToken(tokenValue, user);
			this.userService.save(user);
			return user;
		}
		throw new UnauthorizedException("Unauthorized operation");
	}

	@RequestMapping(value = "/coordinator", method = RequestMethod.POST)
	public @ResponseBody Coordinator createUser(@RequestBody Coordinator user,
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		User authUser = this.userService.getUserByEmail(user.getEmail());
		if (authUser.getRole().equals(Role.COORDINATOR)) {
			this.authService.createToken(tokenValue, user);
			return user;
		}
		throw new UnauthorizedException("Unauthorized operation");
	}

}
