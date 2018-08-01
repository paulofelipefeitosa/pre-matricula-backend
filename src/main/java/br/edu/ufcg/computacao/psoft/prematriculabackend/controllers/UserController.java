package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody User getUser(
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		return this.authService.getUser(tokenValue);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user,
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		this.authService.createToken(tokenValue, user);
		this.userService.save(user);
		return user;
	}

}
