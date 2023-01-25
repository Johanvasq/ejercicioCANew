package co.com.ias.ejercicioCA.infrastructure.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {


    @Bean
    public DBSecret dbSecret(Environment env){
        return DBSecret
                .builder()
                .url(env.getProperty("spring.datasource.url"))
                .user(env.getProperty("spring.datasource.username"))
                .build();
    }

    @Bean
    @Profile("dev")
    public DataSource dataSource(DBSecret dbSecret){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbSecret.getUrl());
        hikariConfig.setUsername(dbSecret.getUser());
        return new HikariDataSource();
    }

    @Bean
    @Profile("test")
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:johan;DB_CLOSE_DELAY=-1");
        hikariConfig.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(hikariConfig);
    }
}
