package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.PreEnrollmentService;

@RestController
@CrossOrigin
@RequestMapping("/preenrollment")
public class PreEnrollmentController {

	@Autowired
	private PreEnrollmentService preEnrollmentService;

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<PreEnrollment> getAllPreEnrollments(
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		User user = this.authService.getUser(tokenValue);
		if (user.getRole().equals(Role.COORDINATOR)) {
			return this.preEnrollmentService.getPreEnrollments();
		}
		throw new UnauthorizedException("Unauthorized operation");
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public @ResponseBody PreEnrollment getUserPreEnrollment(
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		User user = this.authService.getUser(tokenValue);
		if (user.getRole().equals(Role.STUDENT)) {
			return this.preEnrollmentService.getPreEnrollmentByStudentEnrollment(user.getEnrollmentNumber());
		}
		throw new UnauthorizedException("Unauthorized operation");
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public @ResponseBody PreEnrollment createUserPreEnrollment(@RequestBody PreEnrollment preEnrollment,
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		User user = this.authService.getUser(tokenValue);
		if (user.getRole().equals(Role.STUDENT)) {
			return this.preEnrollmentService.save(user, preEnrollment);
		}
		throw new UnauthorizedException("Unauthorized operation");
	}

}
