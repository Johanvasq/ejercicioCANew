package co.com.ias.ejercicioCA.infraestructure.adapters;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseId;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseName;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.CourseAdapterRepository;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.ICourseAdapterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class CourseAdapterRepositoryTest {

    @Autowired
    private ICourseAdapterRepository repository;

    @InjectMocks
    private CourseAdapterRepository courseAdapterRepository;



    @BeforeAll
    void init() {
         CourseAdapterRepository courseAdapterRepository = new
                 CourseAdapterRepository(repository);
    }

    @Test
    @DisplayName("Save Product success")
    void saveProduct() {

        //Arrange
        Course course = new Course(
                new CourseId(10L),
                new CourseName("Name")
        );

        //Act
        Course response = courseAdapterRepository.saveCourse(course);

        //Assert
        assertEquals("Name", response.getName().getValue());
    }

}
