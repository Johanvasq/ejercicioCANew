package co.com.ias.ejercicioCA.infrastructure.adapters;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseId;
import co.com.ias.ejercicioCA.domain.model.course.attributes.CourseName;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.CourseAdapterRepository;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.ICourseAdapterRepository;
import org.aspectj.bridge.IMessage;
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
    ICourseAdapterRepository repository;
    @InjectMocks
    CourseAdapterRepository courseAdapterRepository;

    @BeforeAll
    void init(){
        courseAdapterRepository = new CourseAdapterRepository(repository);
    }

    @Test
    @DisplayName("Save Product success")
    void saveProduct() {

        //Arrange
        Course course1 = new Course(
                new CourseId(10L),
                new CourseName("Name")
        );

        //Act
        Course response1 = courseAdapterRepository.saveCourse(course1);

        //Assert
        assertEquals("Name", response1.getName().getValue(), "passed");
    }

}
