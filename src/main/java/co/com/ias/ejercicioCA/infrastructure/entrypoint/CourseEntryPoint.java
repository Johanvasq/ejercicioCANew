package co.com.ias.ejercicioCA.infrastructure.entrypoint;


import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseEntryPoint {

    private final CourseUseCase courseUseCase;

    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok().body(courseUseCase.saveCourse(courseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<CourseDTO> courseDTO = Optional.ofNullable(
                courseUseCase.findById(id));
        if (courseDTO.isPresent()){
            return ResponseEntity.ok().body(courseDTO.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(courseUseCase.findAll());
    }
}
