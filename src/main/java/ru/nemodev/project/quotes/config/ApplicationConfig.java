package ru.nemodev.project.quotes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.nemodev.project.quotes.config.api.ConverterDtoConfig;
import ru.nemodev.project.quotes.config.api.EndpointConfig;
import ru.nemodev.project.quotes.config.api.SwaggerConfig;
import ru.nemodev.project.quotes.config.cache.CacheConfig;
import ru.nemodev.project.quotes.config.database.DataBaseConfig;
import ru.nemodev.project.quotes.config.property.PropertyConfig;
import ru.nemodev.project.quotes.config.service.ServiceConfig;
import ru.nemodev.project.quotes.config.telegram.TelegramConfig;


/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:46
 */
@Configuration
@Import({PropertyConfig.class,
        DataBaseConfig.class,
        CacheConfig.class,
        ServiceConfig.class,
        TelegramConfig.class,
        ConverterDtoConfig.class,
        EndpointConfig.class,
        SwaggerConfig.class})
public class ApplicationConfig
{ }