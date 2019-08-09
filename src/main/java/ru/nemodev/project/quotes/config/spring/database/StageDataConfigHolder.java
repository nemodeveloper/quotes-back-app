package ru.nemodev.project.quotes.config.spring.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stage")
public class StageDataConfigHolder extends AbstractDataSourceHolder {

    @Override
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource()
    {
        return new HikariDataSource(hikariConfig());
    }
}
