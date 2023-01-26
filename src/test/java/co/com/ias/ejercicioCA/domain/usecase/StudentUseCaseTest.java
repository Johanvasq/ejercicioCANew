package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;
import co.com.ias.ejercicioCA.domain.model.gateway.IStudentRepository;
import co.com.ias.ejercicioCA.domain.model.student.Student;
import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentUseCaseTest {

    @MockBean
    private IStudentRepository iStudentRepository;

    @InjectMocks
    private StudentUseCase studentUseCase;

    @MockBean
    private ICourseRepository iCourseRepository;

    @BeforeAll
    void init(){
        studentUseCase = new StudentUseCase(iStudentRepository,iCourseRepository);
    }

    @Test
    @DisplayName("Saving student")
    void saveStudent() {
        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");
        studentDTO.setCourseId(1L);
        Student student = StudentDTO.toDomain(studentDTO);

        CourseDTO courseDTO = new CourseDTO(1L , "Math");
        Course course = courseDTO.toDomain(courseDTO);


        when(iCourseRepository.findById(1L))
                .thenReturn(course);

        when(iStudentRepository.saveStudent(any(Student.class), any(Course.class)))
                .thenReturn(student);

        StudentDTO result = studentUseCase.saveStudent(studentDTO);

        assertEquals(student.getName().getValue(), result.getName());


    }
    @Test
    @DisplayName("Saving student")
    void testSaveStudent() {
        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");
        studentDTO.setCourseId(null);
        Student student = StudentDTO.toDomain(studentDTO);

        CourseDTO courseDTO = new CourseDTO(1L , "Math");
        Course course = courseDTO.toDomain(courseDTO);


        when(iCourseRepository.findById(1L))
                .thenReturn(course);

        when(iStudentRepository.saveStudent(any(Student.class), any(Course.class)))
                .thenReturn(student);

        StudentDTO result = studentUseCase.saveStudent(studentDTO);

        assertTrue(result == null);


    }

    @Test
    @DisplayName("find all students")
    void findAll() {

        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");
        studentDTO.setCourseId(1L);
        Student student = StudentDTO.toDomain(studentDTO);

        when(iStudentRepository.findAll())
                .thenReturn(List.of(student));

        List<StudentDTO> result = studentUseCase.findAll();

        assertEquals(1, result.size());

    }

    @Test
    @DisplayName("find all by course")
    void findByCourse() {
        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");
        studentDTO.setCourseId(1L);
        Student student = StudentDTO.toDomain(studentDTO);

        CourseDTO courseDTO = new CourseDTO(1L , "Math");
        Course course = courseDTO.toDomain(courseDTO);


        when(iCourseRepository.findById(1L))
                .thenReturn(course);

        when(iStudentRepository.findByCourse(any(Course.class)))
                .thenReturn(List.of(student));

        List<StudentDTO> result = studentUseCase.findByCourse(1L);

        assertEquals(student.getName().getValue(), result.get(0).getName());
        assertEquals(1, result.size());


    }

    @Test
    @DisplayName("find all by course fail")
    void testFindByCourse() {

        when(iCourseRepository.findById(1L))
                .thenReturn(null);

        List<StudentDTO> result = studentUseCase.findByCourse(1L);

        assertFalse(result.size() > 0);


    }

    @Test
    void findById() {
    }
}