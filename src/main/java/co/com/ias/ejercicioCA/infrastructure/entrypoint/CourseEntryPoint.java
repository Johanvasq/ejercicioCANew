package co.com.ias.ejercicioCA.infrastructure.entrypoint;


import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import co.com.ias.ejercicioCA.infrastructure.exception.ClassException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CourseEntryPoint {

    private final CourseUseCase courseUseCase;


    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody CourseDTO courseDTO){
        try {
            return ResponseEntity.status(201).body(courseUseCase.saveCourse(courseDTO));
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ClassException.builder().message(ex.getMessage()).status(500).build()
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Min(1) Long id) {
        try{
            Optional<CourseDTO> courseDTO = Optional.ofNullable(
                    courseUseCase.findById(id));
            if (courseDTO.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(courseDTO.get());
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
            }
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ClassException.builder().message(ex.getMessage()).status(500).build()
            );
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
