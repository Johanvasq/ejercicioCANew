package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course;

import co.com.ias.ejercicioCA.domain.model.course.Course;
import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;
import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo.CourseDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class CourseAdapterRepository implements ICourseRepository {

    private final ICourseAdapterRepository iCourseAdapterRepository;
    @Override
    public Course saveCourse(Course course) {
        CourseDBO courseDBO = new CourseDBO().fromDomain(course);
        CourseDBO response = iCourseAdapterRepository.save(courseDBO);
        return courseDBO.toDomain(response);
    }

    @Override
    public Course findById(Long id) {
        Optional<CourseDBO> courseDBO = iCourseAdapterRepository.findById(id);
        if (courseDBO.isPresent()){
            return courseDBO.get().toDomain(courseDBO.get());
        }else {
            return null;
        }
    }

    @Override
    public List<Course> findAll() {
        return iCourseAdapterRepository.findAll()
                .stream()
                .map(CourseDBO::toDomain)
                .collect(Collectors.toList());
    }
}
