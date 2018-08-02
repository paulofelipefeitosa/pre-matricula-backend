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
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
	public static final String TOKEN_VALUE_HEADER_KEY = "X-Auth-Token";

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String createToken(@RequestBody User user,
			@RequestHeader(required = true, value = TOKEN_VALUE_HEADER_KEY) String tokenValue) {
	    logger.info(tokenValue);
	    logger.info(user.getEmail());
		return this.authService.createToken(tokenValue, user.getEmail());
	}
}
