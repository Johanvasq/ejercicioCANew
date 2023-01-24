package co.com.ias.ejercicioCA.domain.model.student.attributes;

public class StudentName {

    private final String value;

    public StudentName(String value) {
        if (value == null){
            throw new IllegalArgumentException("EL nombre del estudiante es obligatorio");
        }else {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }
}
