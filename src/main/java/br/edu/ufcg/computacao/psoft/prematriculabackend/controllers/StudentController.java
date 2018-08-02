package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.StudentService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Student getStudent(@RequestHeader(required = true,
            value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        Student student = this.studentService.getStudentByEmail(email);
        if (student != null) {
            return student;
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Student createStudent(@RequestBody Student student,
            @RequestHeader(required = true,
                    value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        User user = this.userService.getUserByEmail(email);
        if (user == null || user.getRole().equals(Role.STUDENT)) {
            if (email.equals(student.getEmail())) {
                return this.studentService.save(student);
            }
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

}
