package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseUseCaseTest {

    @MockBean
    private ICourseRepository iCourseRepository;

    @InjectMocks
    private CourseUseCase courseUseCase;

    @BeforeAll
    void init(){
        courseUseCase = new CourseUseCase(iCourseRepository);
    }


    @Test
    @DisplayName("Saving Course")
    void saveCourse() {

        CourseDTO courseDTO = new CourseDTO(1L, "Math");
        Course course = courseDTO.toDomain(courseDTO);

        when(iCourseRepository.saveCourse(any(Course.class)))
                .thenReturn(course);

        CourseDTO respuesta = courseUseCase.saveCourse(courseDTO);

        assertNotNull(respuesta);
        assertEquals("Math", respuesta.getName());

    }

    @Test
    @DisplayName("find by id ok")
    void findById() {

        CourseDTO courseDTO = new CourseDTO(1L, "Math");
        Course course = courseDTO.toDomain(courseDTO);

        when(iCourseRepository.findById(1L))
                .thenReturn(course);

        CourseDTO result = courseUseCase.findById(1L);

        assertEquals("Math" , result.getName());
    }
    @Test
    @DisplayName("find by id fail")
    void testFindById() {

        when(iCourseRepository.findById(1L))
                .thenReturn(null);

        CourseDTO result = courseUseCase.findById(1L);

        assertTrue(result == null);
    }

    @Test
    @DisplayName("Find All OK")
    void findAll() {
        CourseDTO courseDTO = new CourseDTO(1L, "Math");
        Course course = courseDTO.toDomain(courseDTO);

        when(iCourseRepository.findAll())
                .thenReturn(List.of(course));

        List<CourseDTO> result = courseUseCase.findAll();

        assertTrue(result.size() == 1);
    }

    @Test
    @DisplayName("Find All - Fail")
    void testFindAll() {

        when(iCourseRepository.findAll())
                .thenReturn(List.of());

        List<CourseDTO> result = courseUseCase.findAll();

        assertTrue(result.size() == 0);
    }

}