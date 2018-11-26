package ru.nemodev.project.quotes.config.spring.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import ru.nemodev.project.quotes.config.DataBaseProperties;

import java.io.IOException;

public abstract class AbstractDataBaseConfig implements DataBaseSource
{
    @Bean
    public PropertiesFactoryBean dataBasePropertiesFactory()
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setSingleton(true);
        propertiesFactoryBean.setLocations(new ClassPathResource("config/database.properties"));

        return propertiesFactoryBean;
    }

    @Bean
    public DataBaseProperties dataBaseProperties()
    {
        try
        {
            return new DataBaseProperties(dataBasePropertiesFactory().getObject());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource()
    {
        DataBaseProperties dataBaseProperties = dataBaseProperties();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dataBaseProperties.getDriver());
        dataSource.setUrl(dataBaseProperties.getURL());
        dataSource.setUsername(dataBaseProperties.getUsername());
        dataSource.setPassword(dataBaseProperties.getPassword());
        dataSource.setInitialSize(dataBaseProperties.getSocketTimeout());
        dataSource.setMaxIdle(3);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setRemoveAbandoned(true);

        return dataSource;
    }
}
