package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;
import co.com.ias.ejercicioCA.domain.model.gateway.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StudentUseCase {
    private final IStudentRepository iStudentRepository;
    private final ICourseRepository iCourseRepository;

    public StudentUseCase(IStudentRepository iStudentRepository, ICourseRepository iCourseRepository) {
        this.iStudentRepository = iStudentRepository;
        this.iCourseRepository = iCourseRepository;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Optional<Course> course = Optional
                .ofNullable(iCourseRepository.findById(studentDTO.getCourseId()));
        if (course.isPresent()){
            Student student = studentDTO.toDomain(studentDTO);
            return studentDTO.fromDomain(iStudentRepository.saveStudent(student, course.get()));
        }
        return null;
    }

    public List<StudentDTO> findAll() {
        return iStudentRepository.findAll()
                .stream()
                .map(student -> StudentDTO.fromDomain(student))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findByCourse(Long id){
        Optional<Course> course = Optional
                .ofNullable(iCourseRepository.findById(id));
        if(course.isPresent()){
            return iStudentRepository
                    .findByCourse(course.get())
                    .stream()
                    .map(StudentDTO::fromDomain)
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }

    public StudentDTO findById(Long id){
        Optional<Student> student = Optional.ofNullable(iStudentRepository.findById(id));
        if (student.isPresent()){
            return StudentDTO.fromDomain(student.get());
        } else {
            return null;
        }
    }
}
