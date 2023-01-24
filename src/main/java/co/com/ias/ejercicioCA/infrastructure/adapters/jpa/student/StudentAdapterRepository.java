package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.student;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.gateway.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo.CourseDBO;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.student.entity.StudentDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class StudentAdapterRepository implements IStudentRepository {
    private final IStudentAdapterRepository iStudentAdapterRepository;
    @Override
    public Student saveStudent(Student student, Course course) {
        StudentDBO studentDBO = new StudentDBO().fromDomain(student);
        CourseDBO courseDBO = new CourseDBO().fromDomain(course);
        studentDBO.setCourseDBO(courseDBO);
        return studentDBO.toDomain(iStudentAdapterRepository.save(studentDBO));
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = iStudentAdapterRepository.findAll()
                .stream()
                .map(student -> new StudentDBO().toDomain(student))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Student> findByCourse(Long id) {
        return iStudentAdapterRepository.findByCourseId(id)
                .stream()
                .map(StudentDBO::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Student findById(Long id) {
        Optional<StudentDBO> student = iStudentAdapterRepository.findById(id);
        if(student.isPresent()){
            return StudentDBO.toDomain(student.get());
        }else {
            return null;
        }

    }


}
