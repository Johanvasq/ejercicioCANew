package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.student;

import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo.CourseDBO;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.student.entity.StudentDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentAdapterRepository extends JpaRepository<StudentDBO, Long> {

    //List<StudentDBO> findByCourseDBO_Name(String name);
    //List<StudentDBO> findByCourseDBO_Id(Long id);

    List<StudentDBO> findByCourseDBO(CourseDBO courseDBO);
}
