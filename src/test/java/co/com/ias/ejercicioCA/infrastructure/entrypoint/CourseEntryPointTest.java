package co.com.ias.ejercicioCA.infrastructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CourseEntryPoint.class)
public class CourseEntryPointTest {

    @MockBean
    private CourseUseCase courseUseCase;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("save course ok")
    void saveCourse() throws Exception {
        //Arrange
        CourseDTO courseDTO = new CourseDTO(1L, "Fisica");

        when(courseUseCase.saveCourse(any(CourseDTO.class)))
                .thenReturn(courseDTO);

        ObjectMapper mapper = new ObjectMapper();
        //Act && Assert
        mockMvc.perform(
                post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(courseDTO))
        ).andExpect(status().isCreated())
                .andExpect(content().string(containsString(courseDTO.getName())));
    }


    @Test
    @DisplayName("find course by id FOUND")
    void findById() throws Exception {

        CourseDTO courseDTO = new CourseDTO(1L, "Fisica");

        when(courseUseCase.findById(any(Long.class)))
                .thenReturn(courseDTO);

        ObjectMapper mapper = new ObjectMapper();

        //Act && Assert
        mockMvc.perform(
                get("/course/1"))
                .andExpect(status().isFound())
                .andExpect(content().string(containsString(courseDTO.getName())));

    }

    @Test
    @DisplayName("find course by id NOT_FOUND")
    void testFindById() throws Exception {

        when(courseUseCase.findById(any(Long.class)))
                .thenReturn(any());

        ObjectMapper mapper = new ObjectMapper();

        //Act && Assert
        mockMvc.perform(
                        get("/course/1"))
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("find all courses ok")
    void findAll() throws Exception {
        CourseDTO courseDTO = new CourseDTO(1L, "Fisica");

        when(courseUseCase.findAll())
              .thenReturn(List.of(courseDTO));

        //Act && Assert
        mockMvc.perform(
                get("/course"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Fisica")));

    }

    @Test
    @DisplayName("not found courses")
    void testFindAll() throws Exception {

        when(courseUseCase.findAll())
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/course"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course not found"));
    }


}
