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
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.CoordinatorService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.CourseService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CoordinatorService coordService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Course> getAllCourses(@RequestHeader(required = true,
            value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            return this.courseService.getCourses();
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Course createCourse(@RequestBody Course course,
            @RequestHeader(required = true,
                    value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        Coordinator coord = this.coordService.getCoordinatorByEmail(email);
        if (coord != null) {
            return this.courseService.save(course);
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

}
