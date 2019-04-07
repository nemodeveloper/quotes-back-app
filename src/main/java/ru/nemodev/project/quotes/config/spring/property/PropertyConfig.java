package ru.nemodev.project.quotes.config.spring.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Import({SystemProperty.class,
        DataBaseProperty.class,
        TelegramProperty.class})
public class PropertyConfig
{
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(
                new ClassPathResource("config/system.properties"),
                new ClassPathResource("config/database.properties"),
                new ClassPathResource("config/telegram.properties"));
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);

        return propertySourcesPlaceholderConfigurer;
    }

}
