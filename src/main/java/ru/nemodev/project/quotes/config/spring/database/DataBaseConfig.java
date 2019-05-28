package ru.nemodev.project.quotes.config.spring.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:21
 */
@Configuration
@Import({DevRemoteDataConfigHolder.class,
        DevEmbeddedDataConfigHolder.class,
        StageDataConfigHolder.class,
        ProdDataConfigHolder.class,
        HibernateConfig.class})
public class DataBaseConfig
{ }
