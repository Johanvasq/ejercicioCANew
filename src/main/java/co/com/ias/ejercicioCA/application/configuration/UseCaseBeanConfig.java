package co.com.ias.ejercicioCA.application.configuration;

import co.com.ias.ejercicioCA.domain.model.gateway.ICourseRepository;
import co.com.ias.ejercicioCA.domain.model.gateway.IStudentRepository;
import co.com.ias.ejercicioCA.domain.usecase.CourseUseCase;
import co.com.ias.ejercicioCA.domain.usecase.StudentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {
    @Bean
    public StudentUseCase studentUseCase(
            IStudentRepository iStudentRepository,
            CourseUseCase courseUseCase){
        return new StudentUseCase(iStudentRepository, courseUseCase);
    }

    @Bean
    public CourseUseCase courseUseCase(ICourseRepository iCourserepository){
        return new CourseUseCase(iCourserepository);
    }
}
