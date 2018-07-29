package br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment;

import org.springframework.stereotype.Component;

@Component
public class DefaultPreEnrollmentValidator implements PreEnrollmentValidator {

    public static final Integer MINIMUM_TOTAL_CREDITS = 16;
    public static final Integer MAXIMUM_TOTAL_CREDITS = 24;

    @Override
    public Status getPreEnrollmentStatus(PreEnrollment preEnrollment) {
        Integer totalCredits = preEnrollment.getCourses()
                .stream()
                .map(e -> e.getCredits())
                .reduce(0, (a, b) -> a + b);
        if (totalCredits >= MINIMUM_TOTAL_CREDITS && totalCredits <= MAXIMUM_TOTAL_CREDITS) {
            return Status.VALIDA;
        } else {
            return Status.INVALIDA;
        }
    }

}
