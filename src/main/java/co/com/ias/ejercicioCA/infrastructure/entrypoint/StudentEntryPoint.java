package co.com.ias.ejercicioCA.infrastructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentEntryPoint {

   private final StudentUseCase studentUseCase;

   @PostMapping
   public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO){
       Optional<StudentDTO> student = Optional.ofNullable(
               studentUseCase.saveStudent(studentDTO));

       if(student.isPresent()){
           return ResponseEntity.ok().body(student.get());
       }else {
           return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                   .body("Ingrese un id de curso valido");
       }
   }

   @GetMapping
   public ResponseEntity<?> findAll(){
       return ResponseEntity.ok().body(studentUseCase.findAll());
   }


   @GetMapping("/course/{id}")
    public ResponseEntity<?> findByCourse(@PathVariable Long id){
       Optional<List<StudentDTO>> list = Optional.ofNullable(studentUseCase.findByCourse(id));
       if (list.isPresent() && list.get().size() > 0){
           return ResponseEntity.ok().body(list.get());
       }else if (list.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrese un id de materia valido");
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay estudiantes registrados en la materia");
       }
   }

   @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
       Optional<StudentDTO> studentDTO = Optional.ofNullable(studentUseCase.findById(id));
       if (studentDTO.isPresent()){
           return ResponseEntity.ok().body(studentDTO.get());
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante no fue encontrado");
       }
   }

}