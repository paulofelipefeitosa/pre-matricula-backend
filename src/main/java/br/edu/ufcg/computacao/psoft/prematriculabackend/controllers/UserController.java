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

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	public static final String TOKEN_VALUE_HEADER_KEY = "X-Auth-Token";

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody User user(@RequestHeader(required = true, value = TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		return this.authService.getUser(tokenValue);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User user(@RequestBody User user,
			@RequestHeader(required = true, value = TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		this.authService.createToken(tokenValue, user);
		return user;
	}

}
