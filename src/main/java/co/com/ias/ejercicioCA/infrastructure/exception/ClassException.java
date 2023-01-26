package co.com.ias.ejercicioCA.infrastructure.exception;

import lombok.Builder;

@Builder
public class ClassException {

    private final Integer status;
    private final String message;

}
