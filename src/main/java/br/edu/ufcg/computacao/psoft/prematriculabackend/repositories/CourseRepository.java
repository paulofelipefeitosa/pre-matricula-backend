package br.edu.ufcg.computacao.psoft.prematriculabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;

public interface CourseRepository extends JpaRepository<Course, String> {
}