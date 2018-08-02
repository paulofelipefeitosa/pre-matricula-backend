package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment.PreEnrollment;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.PreEnrollmentService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.StudentService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/preenrollments")
public class PreEnrollmentController {

    @Autowired
    private PreEnrollmentService preEnrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationService authService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<PreEnrollment> getAllPreEnrollments(@RequestHeader(required = true,
            value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        User user = this.userService.getUserByEmail(email);
        if (user != null && user.getRole().equals(Role.COORDINATOR)) {
            return this.preEnrollmentService.getPreEnrollments();
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

    @RequestMapping(value = "/{studentEnrollment}", method = RequestMethod.GET)
    public @ResponseBody PreEnrollment getPreEnrollment(@PathVariable String studentEnrollment,
            @RequestHeader(required = true,
                    value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            PreEnrollment preEnrollment = this.preEnrollmentService
                    .getPreEnrollmentByStudentEnrollment(studentEnrollment);
            if (user.getRole().equals(Role.COORDINATOR)) {
                return preEnrollment;
            } else {
                String userEnrollment = user.getEnrollmentNumber();
                if (studentEnrollment.equals(userEnrollment)) {
                    return preEnrollment;
                }
            }
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody PreEnrollment createUserPreEnrollment(
            @RequestBody PreEnrollment preEnrollment, @RequestHeader(required = true,
                    value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        Student student = this.studentService.getStudentByEmail(email);
        if (student != null) {
            return this.preEnrollmentService.save(student, preEnrollment);
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

}
