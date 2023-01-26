package co.com.ias.ejercicioCA.domain.model.student.attributes;

import java.util.regex.Pattern;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class StudentPhone {

    private final Integer value;

    public StudentPhone(Integer value) {
        notNull(value, "Name can't by null");
        isTrue(Pattern.matches("^-?[0-9]+$",value.toString()), "Value only can be an integer");
        this.value = value;
    }

    public Integer getValue() {

        return value;
    }
}
