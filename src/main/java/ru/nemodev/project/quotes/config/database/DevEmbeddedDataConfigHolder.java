package ru.nemodev.project.quotes.config.database;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:11
 */
@Configuration
@Profile("dev-db-embedded")
public class DevEmbeddedDataConfigHolder implements DataSourceHolder
{
    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(false)
                .setName("quotes-h2-db")
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("config/schema.sql", "config/data.sql")
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }
}