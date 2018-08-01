package br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;

public class PreEnrollment {
    private String studentEnrollment;
    private String semester;
    private List<Course> courses;
    private Status status;
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    @Autowired
    private PreEnrollmentValidator validator;

    public PreEnrollment(String studentEnrollment, String semester, List<Course> courses) {
        this.studentEnrollment = studentEnrollment;
        this.semester = semester;
        this.courses = courses;
        this.status = this.validator.getPreEnrollmentStatus(this);
    }

    public String getSemester() {
        return semester;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getStudentEnrollment() {
        return studentEnrollment;
    }
    
    public Status getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((semester == null) ? 0 : semester.hashCode());
        result = prime * result + ((studentEnrollment == null) ? 0 : studentEnrollment.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PreEnrollment other = (PreEnrollment) obj;
        if (semester == null) {
            if (other.semester != null)
                return false;
        } else if (!semester.equals(other.semester))
            return false;
        if (studentEnrollment == null) {
            if (other.studentEnrollment != null)
                return false;
        } else if (!studentEnrollment.equals(other.studentEnrollment))
            return false;
        return true;
    }

}
