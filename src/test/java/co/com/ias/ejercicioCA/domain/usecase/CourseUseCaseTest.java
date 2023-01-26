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
}