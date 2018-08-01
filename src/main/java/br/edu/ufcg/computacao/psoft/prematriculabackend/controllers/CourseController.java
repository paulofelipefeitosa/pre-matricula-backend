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

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.CourseService;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Course> getAllCourses(
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		this.authService.getUser(tokenValue);
		return this.courseService.getCourses();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Course createCourse(@RequestBody Course course,
			@RequestHeader(required = true, value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
		User user = this.authService.getUser(tokenValue);
		if (user.getRole().equals(Role.COORDINATOR)) {
			return this.courseService.save(course);
		}
		throw new UnauthorizedException("Unauthorized operation");
	}
	
}
