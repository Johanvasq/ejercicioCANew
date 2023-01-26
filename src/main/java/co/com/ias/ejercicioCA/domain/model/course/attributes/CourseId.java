package co.com.ias.ejercicioCA.domain.model.course.attributes;

import org.springframework.util.Assert;

import static java.lang.Math.min;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class CourseId {

    private final Long value;

    public CourseId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
