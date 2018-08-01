package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.CourseRepository;

public class CourseService {
	@Autowired
	CourseRepository courseRepository;

	public List<Course> rse() {
		return this.courseRepository.findAll();
	}
	
	public Course save(Course course) {
		this.courseRepository.save(course);
		return course;
	}
	
}
