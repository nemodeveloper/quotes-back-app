package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.nemodev.project.quotes.config.spring.database.DataBaseConfig;


/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:46
 */
@Configuration
@Import({DataBaseConfig.class,
        CacheConfig.class,
        ServiceConfig.class,
        TelegramConfig.class})
public class ApplicationConfig
{ }