package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getCourses() {
		return this.courseRepository.findAll();
	}

	public Course save(Course course) {
		this.courseRepository.save(course);
		return course;
	}

}