package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public @ResponseBody Collection<Course> getAllCourses() {
        return this.courseService.getAll();
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public @ResponseBody Course getCourse(@PathVariable String code) {
        return this.courseService.getByCode(code);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('COORDINATOR')")
    public @ResponseBody Course createCourse(@RequestBody Course course) {
        this.courseService.create(course);
        return course;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('COORDINATOR')")
    public @ResponseBody void deleteCourse(@PathVariable String code) {
        this.courseService.delete(code);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('COORDINATOR')")
    public @ResponseBody void deleteAllCourses() {
        this.courseService.deleteAll();
    }
}
