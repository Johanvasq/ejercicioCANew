package co.com.ias.ejercicioCA.domain.model.gateway;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.student.Student;

import java.util.List;

public interface IStudentRepository {
    Student saveStudent(Student student, Course course);

    List<Student> findAll();

    List<Student> findByCourse(Course course);

    Student findById(Long id);
}
