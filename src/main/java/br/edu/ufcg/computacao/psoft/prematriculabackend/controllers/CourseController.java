package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.CourseService;

@Controller
@RequestMapping(value = CourseController.COURSE_ENDPOINT)
public class CourseController {

    public static final String COURSE_ENDPOINT = "/courses";

    @Autowired
    private CourseService courseService;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Course> getAllCourses(Authentication authentication) {
        
    }
}
