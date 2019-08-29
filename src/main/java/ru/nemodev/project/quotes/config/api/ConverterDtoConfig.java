package ru.nemodev.project.quotes.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.project.quotes.api.converter.AuthorToDTOConverter;
import ru.nemodev.project.quotes.api.converter.CategoryToDTOConverter;
import ru.nemodev.project.quotes.api.converter.QuoteToDTOConverter;
import ru.nemodev.project.quotes.config.property.SystemProperty;

@Configuration
public class ConverterDtoConfig
{
    private final SystemProperty systemProperty;

    public ConverterDtoConfig(SystemProperty systemProperty)
    {
        this.systemProperty = systemProperty;
    }

    @Bean
    public AuthorToDTOConverter authorToDTOConverter()
    {
        return new AuthorToDTOConverter(systemProperty);
    }

    @Bean
    public CategoryToDTOConverter categoryToDTOConverter()
    {
        return new CategoryToDTOConverter(systemProperty);
    }

    @Bean
    public QuoteToDTOConverter quoteToDTOConverter()
    {
        return new QuoteToDTOConverter(authorToDTOConverter(), categoryToDTOConverter());
    }
}
