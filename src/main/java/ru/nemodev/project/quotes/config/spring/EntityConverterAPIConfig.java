package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.project.quotes.api.converter.AuthorToDTOConverter;
import ru.nemodev.project.quotes.api.converter.CategoryToDTOConverter;
import ru.nemodev.project.quotes.api.converter.QuoteToDTOConverter;
import ru.nemodev.project.quotes.config.SystemProperties;

@Configuration
public class EntityConverterAPIConfig
{
    private final SystemProperties systemProperties;

    public EntityConverterAPIConfig(SystemProperties systemProperties)
    {
        this.systemProperties = systemProperties;
    }

    @Bean
    public AuthorToDTOConverter authorToDTOConverter()
    {
        return new AuthorToDTOConverter(systemProperties);
    }

    @Bean
    public CategoryToDTOConverter categoryToDTOConverter()
    {
        return new CategoryToDTOConverter(systemProperties);
    }

    @Bean
    public QuoteToDTOConverter quoteToDTOConverter()
    {
        return new QuoteToDTOConverter(authorToDTOConverter(), categoryToDTOConverter());
    }
}
