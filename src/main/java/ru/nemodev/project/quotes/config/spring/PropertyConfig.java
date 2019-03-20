package ru.nemodev.project.quotes.config.spring;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.nemodev.project.quotes.config.SystemProperties;

import java.io.IOException;

@Configuration
public class PropertyConfig
{
    @Bean
    public PropertiesFactoryBean systemPropertiesFactory()
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setSingleton(true);
        propertiesFactoryBean.setLocations(new ClassPathResource("config/system.properties"));

        return propertiesFactoryBean;
    }

    @Bean
    public SystemProperties systemProperties()
    {
        try
        {
            return new SystemProperties(systemPropertiesFactory().getObject());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
