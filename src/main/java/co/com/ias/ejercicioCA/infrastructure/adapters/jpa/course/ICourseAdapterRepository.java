package co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course;

import co.com.ias.ejercicioCA.infrastructure.adapters.jpa.course.dbo.CourseDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseAdapterRepository extends JpaRepository<CourseDBO, Long> {
}
