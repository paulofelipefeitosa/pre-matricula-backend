package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.student;

import java.util.List;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.course.Course;

public class PreEnrollment {
    private String studentEnrollment;
    private String semester;
    private List<Course> courses;

    public PreEnrollment(String studentEnrollment, String semester, List<Course> courses) {
        this.studentEnrollment = studentEnrollment;
        this.semester = semester;
        this.courses = courses;
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
