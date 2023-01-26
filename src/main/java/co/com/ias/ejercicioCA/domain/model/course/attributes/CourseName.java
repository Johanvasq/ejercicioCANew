package co.com.ias.ejercicioCA.domain.model.course.attributes;

import java.util.regex.Pattern;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class CourseName {

    private final String value;

    public CourseName(String value) {
        notNull(value, "Name can't by null");
        isTrue(Pattern.matches("[aA-zZ ]+",value), "Name can only contain letters and spaces");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
