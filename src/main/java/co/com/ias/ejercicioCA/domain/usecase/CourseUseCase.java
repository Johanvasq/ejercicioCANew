package co.com.ias.ejercicioCA.domain.usecase;

import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseUseCase {

    private final ICourseRepository iCourserepository;

    public CourseUseCase(ICourseRepository iCourserepository) {
        this.iCourserepository = iCourserepository;
    }

    public CourseDTO saveCourse(CourseDTO courseDTO){
        return courseDTO.fromDomain(iCourserepository.saveCourse(courseDTO.toDomain(courseDTO)));
    }

    public CourseDTO findById(Long id){
        Optional<CourseDTO>courseDTO = Optional.ofNullable(
                new CourseDTO().fromDomain(iCourserepository.findById(id)));
        if (courseDTO.isPresent()){
            return courseDTO.get();
        }else {
            return null;
        }
    }

    public List<CourseDTO> findAll() {
        return iCourserepository.findAll()
                .stream()
                .map(CourseDTO::fromDomain)
                .collect(Collectors.toList());
    }
}
