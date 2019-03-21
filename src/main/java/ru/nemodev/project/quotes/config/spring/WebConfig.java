package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.nemodev.project.quotes.api.processor.AuthorRestRequestProcessor;
import ru.nemodev.project.quotes.api.processor.CategoryRestRequestProcessor;
import ru.nemodev.project.quotes.api.processor.QuoteRestRequestProcessor;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:35
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "ru.nemodev.project.quotes.api" })
public class WebConfig implements WebMvcConfigurer
{
    private final ServiceConfig serviceConfig;
    private final EntityConverterAPIConfig entityConverterAPIConfig;

    public WebConfig(ServiceConfig serviceConfig, EntityConverterAPIConfig entityConverterAPIConfig)
    {
        this.serviceConfig = serviceConfig;
        this.entityConverterAPIConfig = entityConverterAPIConfig;
    }

    @Bean
    public AuthorRestRequestProcessor authorRestRequestProcessor()
    {
        return new AuthorRestRequestProcessor(serviceConfig.authorServiceImpl(), entityConverterAPIConfig.authorToDTOConverter());
    }

    @Bean
    public CategoryRestRequestProcessor categoryRestRequestProcessor()
    {
        return new CategoryRestRequestProcessor(serviceConfig.categoryServiceImpl(), entityConverterAPIConfig.categoryToDTOConverter());
    }

    @Bean
    public QuoteRestRequestProcessor quoteRestRequestProcessor()
    {
        return new QuoteRestRequestProcessor(serviceConfig.quoteServiceImpl(), entityConverterAPIConfig.quoteToDTOConverter());
    }
}
