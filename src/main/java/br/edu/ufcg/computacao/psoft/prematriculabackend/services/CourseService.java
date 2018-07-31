package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.NoSuchElementException;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<Course>();

    public List<Course> getAll() {
        return this.courseList;
    }

    public Course create(Course course) {
        this.courseList.add(course);
        return this.courseList.get(this.courseList.size() - 1);
    }

    public Course getByCode(String code) {
        for (Course course : this.courseList) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        throw new NoSuchElementException("There is not course with code [" + code + "]");
    }

    public void delete(String code) {
        Course course = this.getByCode(code);
        this.courseList.remove(course);
    }

    public void deleteAll() {
        this.courseList = new ArrayList<Course>();
    }
}
