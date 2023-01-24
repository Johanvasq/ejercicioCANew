package co.com.ias.ejercicioCA.domain.model.course.dto;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseId;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseName;

public class CourseDTO {

    private Long id;
    private String name;

    public CourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Course toDomain(CourseDTO courseDTO){
        return new Course(
                new CourseId(courseDTO.getId()),
                new CourseName(courseDTO.getName())
        );
    }

    public static CourseDTO fromDomain(Course course) {
        return new CourseDTO(
                course.getId().getValue(),
                course.getName().getValue()
        );
    }
}
