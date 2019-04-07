package ru.nemodev.project.quotes.config.spring.database;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
@Profile("stage")
public class StageDataBaseConfig extends AbstractDataBaseConfig
{
    @Bean
    @Override
    public Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        hibernateProperties.setProperty(Environment.SHOW_SQL, "true");

        return hibernateProperties;
    }
}
