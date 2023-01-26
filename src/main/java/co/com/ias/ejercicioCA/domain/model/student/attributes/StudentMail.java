package co.com.ias.ejercicioCA.domain.model.student.attributes;

import java.util.regex.Pattern;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class StudentMail {

    private final String value;

    public StudentMail(String value) {
        notNull(value, "email can't by null");
        isTrue(Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",value),
                "Please provide a valid email");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
