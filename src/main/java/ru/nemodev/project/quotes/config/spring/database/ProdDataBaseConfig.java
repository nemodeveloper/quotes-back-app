package ru.nemodev.project.quotes.config.spring.database;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:11
 */
@Configuration
@Profile("prod")
public class ProdDataBaseConfig extends AbstractDataBaseConfig
{
    // TODO вынести настройки в файл
    @Bean
    @Override
    public Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        hibernateProperties.setProperty(Environment.SHOW_SQL, "false");

        return hibernateProperties;
    }
}
