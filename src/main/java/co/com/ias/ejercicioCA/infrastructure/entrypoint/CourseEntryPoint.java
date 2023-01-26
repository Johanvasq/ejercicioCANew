package co.com.ias.ejercicioCA.infrastructure.entrypoint;


import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseEntryPoint {

    private final CourseUseCase courseUseCase;


    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.status(201).body(courseUseCase.saveCourse(courseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<CourseDTO> courseDTO = Optional.ofNullable(
                courseUseCase.findById(id));
        if (courseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(courseDTO.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CourseDTO> courses = courseUseCase.findAll();
        if (courses.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }else {
            return ResponseEntity.status(HttpStatus.FOUND).body(courses);
        }
    }
}
