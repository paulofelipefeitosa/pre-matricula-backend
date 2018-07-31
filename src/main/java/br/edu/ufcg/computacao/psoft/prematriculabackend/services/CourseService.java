package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<Course>();

    public Course create(Course course) {
        this.courseList.add(course);
        return this.courseList.get(this.courseList.size() - 1);
    }

    public Course getCourseByCode(String code) {
        for (Course course : this.courseList) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public void delete(User user) {
        if (this.courseList.contains(user)) {
            this.courseList.remove(user);
        }
    }
}
