package ru.nemodev.project.quotes.config.spring.property;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

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

    @Bean
    public Properties hibernateProperties()
    {
        try
        {
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
            propertiesFactoryBean.setLocations(new ClassPathResource("config/hibernate.properties"));
            propertiesFactoryBean.afterPropertiesSet();

            return propertiesFactoryBean.getObject();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
