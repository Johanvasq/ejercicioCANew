package co.com.ias.ejercicioCA.infraestructure.entrypoint;

import co.com.ias.ejercicioCA.domain.model.course.dto.CourseDTO;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import co.com.ias.ejercicioCA.infrastructure.entrypoint.CourseEntryPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
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
    void saveCourseSuccess() throws Exception {
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
        ).andExpect(status().isOk())
                .andExpect(content().string(containsString(courseDTO.getName()) ));
    }
}
