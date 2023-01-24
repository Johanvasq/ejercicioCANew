package co.com.ias.ejercicioCA.domain.model.course.attributes;

public class CourseName {

    private final String value;

    public CourseName(String value) {
        if (value == null){
            throw new IllegalArgumentException("EL nombre del curso es obligatorio");
        }else {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }
}
