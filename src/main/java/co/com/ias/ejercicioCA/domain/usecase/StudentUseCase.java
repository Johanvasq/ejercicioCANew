package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.model.gateway.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StudentUseCase {
    private final IStudentRepository iStudentRepository;
    private final CourseUseCase courseUseCase;

    public StudentUseCase(IStudentRepository iStudentRepository, CourseUseCase courseUseCase) {
        this.iStudentRepository = iStudentRepository;
        this.courseUseCase = courseUseCase;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Optional<CourseDTO> courseDTO = Optional
                .ofNullable(courseUseCase.findById(studentDTO.getCourseId()));
        if (courseDTO.isPresent()){
            Student student = studentDTO.toDomain(studentDTO);
            Course course = courseDTO.get().toDomain(courseDTO.get());
            return studentDTO.fromDomain(iStudentRepository.saveStudent(student, course));
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
        Optional<CourseDTO> courseDTO = Optional
                .ofNullable(courseUseCase.findById(id));
        if(courseDTO.isPresent()){
            return iStudentRepository
                    .findByCourse(id)
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
