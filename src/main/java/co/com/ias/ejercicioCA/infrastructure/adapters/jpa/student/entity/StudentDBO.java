package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.student.entity;

import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.student.attributes.StudentId;
import co.com.ias.ejercicioCA.domain.model.student.attributes.StudentMail;
import co.com.ias.ejercicioCA.domain.model.student.attributes.StudentName;
import co.com.ias.ejercicioCA.domain.model.student.attributes.StudentPhone;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo.CourseDBO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "StudentDBO.findByCourseDBO_Id", query = "select s from StudentDBO s where s.courseDBO.id = :id")
})
@Entity
public class StudentDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer phone;
    private String mail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseDBO courseDBO;


    public StudentDBO(Long id, String name, Integer phone, String mail) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public StudentDBO fromDomain (Student student) {
        return new StudentDBO(
                student.getId().getValue(),
                student.getName().getValue(),
                student.getPhone().getValue(),
                student.getMail().getValue()
        );
    }

    public static Student toDomain(StudentDBO studentDBO) {
        return new Student(
                new StudentId(studentDBO.getId()),
                new StudentName(studentDBO.getName()),
                new StudentPhone(studentDBO.getPhone()),
                new StudentMail(studentDBO.getMail())
        );
    }
}
