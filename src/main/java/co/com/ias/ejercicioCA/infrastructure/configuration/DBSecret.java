package co.com.ias.ejercicioCA.infrastructure.configuration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DBSecret {
    private final String url;
    private final String user;
}
