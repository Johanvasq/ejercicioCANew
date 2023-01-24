package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo;


import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseId;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CourseDBO fromDomain(Course course){
        return new CourseDBO(
                course.getId().getValue(),
                course.getName().getValue()
        );
    }

    public static Course toDomain(CourseDBO courseDBO){
        return new Course(
                new CourseId(courseDBO.getId()),
                new CourseName(courseDBO.getName())
        );
    }

}
