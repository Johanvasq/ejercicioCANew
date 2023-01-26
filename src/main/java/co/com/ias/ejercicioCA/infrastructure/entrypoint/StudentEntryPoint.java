package co.com.ias.ejercicioCA.infrastructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import co.com.ias.ejercicioCA.infrastructure.exception.ClassException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentEntryPoint {

   private final StudentUseCase studentUseCase;

   @PostMapping
   public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO){
       try{
           Optional<StudentDTO> student = Optional.ofNullable(
                   studentUseCase.saveStudent(studentDTO));

           if(student.isPresent()){
               return ResponseEntity.status(201).body(student.get());
           }else {
               return ResponseEntity.status(412)
                       .body("Course id not found");

       }}catch (IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ClassException.builder().message(ex.getMessage()).status(500).build()
        );
       }
   }

   @GetMapping
   public ResponseEntity<?> findAll(){
       List<StudentDTO> students = studentUseCase.findAll();
       if (students.isEmpty()){
           return ResponseEntity.status(404).body("Students not found");
       }else {
           return ResponseEntity.status(HttpStatus.FOUND).body(students);
       }
   }


   @GetMapping("/course/{id}")
    public ResponseEntity<?> findByCourse(@PathVariable @Min(1) Long id){
       Optional<List<StudentDTO>> list = Optional.ofNullable(studentUseCase.findByCourse(id));
       if (list.isPresent() && list.get().size() > 0){
           return ResponseEntity.status(HttpStatus.FOUND).body(list.get());
       }else if (list.isEmpty()){
           return ResponseEntity.status(412).body("Ingrese un id de materia valido");
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay estudiantes registrados en la materia");
       }
   }

   @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Min(1) Long id){
       Optional<StudentDTO> studentDTO = Optional.ofNullable(studentUseCase.findById(id));
       if (studentDTO.isPresent()){
           return ResponseEntity.status(HttpStatus.FOUND).body(studentDTO.get());
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante no fue encontrado");
       }
   }

}
