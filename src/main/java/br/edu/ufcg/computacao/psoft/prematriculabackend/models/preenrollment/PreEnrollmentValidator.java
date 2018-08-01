package br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment;

import java.util.List;

import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;

public class PreEnrollmentValidator {
	public static final Integer MINIMUM_TOTAL_CREDITS = 16;
	public static final Integer MAXIMUM_TOTAL_CREDITS = 24;

	public static Status getPreEnrollmentStatus(PreEnrollment preEnrollment) {
		List<Course> courses = preEnrollment.getCourses();
		if (courses != null && !courses.isEmpty()) {
			Integer totalCredits = courses.stream().map(e -> e.getCredits()).reduce(0, (a, b) -> a + b);
			if (totalCredits >= MINIMUM_TOTAL_CREDITS && totalCredits <= MAXIMUM_TOTAL_CREDITS) {
				return Status.VALIDA;
			}
		}
		return Status.INVALIDA;
	}
}
