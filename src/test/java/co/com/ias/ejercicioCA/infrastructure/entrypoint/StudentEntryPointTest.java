package co.com.ias.ejercicioCA.infrastructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.model.student.dto.StudentDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
;

@WebMvcTest(StudentEntryPoint.class)
class StudentEntryPointTest {

    @MockBean
    private StudentUseCase studentUseCase;

    @Mock
    private CourseUseCase courseUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("save student ok")
    void saveStudent() throws  Exception {
        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");
        studentDTO.setCourseId(1L);

        CourseDTO courseDTO = new CourseDTO(1L, "Fisica");

        when(courseUseCase.saveCourse(any(CourseDTO.class)))
                .thenReturn(courseDTO);

        when(studentUseCase.saveStudent(any(StudentDTO.class)))
                .thenReturn(studentDTO);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                        post("/student")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(studentDTO))
                ).andExpect(status().isCreated())
                .andExpect(content().string(containsString(studentDTO.getName())));
    }

    @Test
    @DisplayName("find all ok")
    void findAll() throws Exception {

        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");

        when(studentUseCase.findAll())
                .thenReturn(List.of(studentDTO));

        mockMvc.perform(
                        get("/student")
                ).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Maria")))
                .andExpect(jsonPath("$[0].phone", is(123))
                );

    }

    @Test
    @DisplayName("find all NOT FOUND")
    void testFindAll() throws Exception {

        when(studentUseCase.findAll())
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/student")
                ).andExpect(status().isNotFound())
                .andExpect(content().string("Students not found"));

    }

    @Test
    @DisplayName("find by course ok")
    void findByCourse() throws Exception {

        StudentDTO studentDTO = new StudentDTO(1L,"Maria", 123, "example@ex.com");

        when(studentUseCase.findByCourse(1L))
                .thenReturn(List.of(studentDTO));

        mockMvc.perform(
                        get("/student/course/1")
                ).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Maria")))
                .andExpect(jsonPath("$[0].phone", is(123))
                );
    }

    @Test
    @DisplayName("find by course 412")
    void testFindByCourse() throws Exception {

        when(studentUseCase.findByCourse(1L))
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/student/course/1")
                ).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("find by course 402")
    void testFindByCourse1() throws Exception {

        when(studentUseCase.findByCourse(1L))
                .thenReturn(null);

        mockMvc.perform(
                get("/student/course/1")
        ).andExpect(status().isPreconditionFailed());
    }

    @Test
    @DisplayName("find by id ok")
    void findById() throws Exception {

        StudentDTO s = new StudentDTO(1L,"Maria", 123, "example");

        when(studentUseCase.findById(1L))
                .thenReturn(s);

        mockMvc.perform(
                        get("/student/1")
                ).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Maria")))
                .andExpect(jsonPath("$.phone", is(123))
                );
    }

    @Test
    @DisplayName("find by id NOT FOUND")
    void testFindById() throws Exception {

        when(studentUseCase.findById(1L))
                .thenReturn(null);

        mockMvc.perform(
                        get("/student/1")
                ).andExpect(status().isNotFound());
    }
}