package co.com.ias.ejercicioCA.domain.model.gateway;

import co.com.ias.ejercicioCA.domain.model.course.Course;

import java.util.List;

public interface ICourseRepository {


    Course saveCourse(Course course);

    Course findById(Long id);

    List<Course> findAll();
}
