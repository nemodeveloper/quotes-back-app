package ru.nemodev.project.quotes.config.spring.database;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import ru.nemodev.project.quotes.config.spring.property.DataBaseProperty;

import javax.sql.DataSource;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:11
 */
@Configuration
@Profile("dev-db-remote")
public class DevRemoteDataConfigHolder implements DataSourceHolder
{
    @Autowired
    private DataBaseProperty dataBaseProperty;

    @Bean
    @DependsOn("h2EmbeddedServer")
    public DataSource dataSource()
    {
        try
        {
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(dataBaseProperty.getDriver());
            dataSource.setUrl(dataBaseProperty.getUrl());
            dataSource.setUsername(dataBaseProperty.getUsername());
            dataSource.setPassword(dataBaseProperty.getPassword());

            final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("config/schema.sql"));
            populator.addScript(new ClassPathResource("config/data.sql"));

            final DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            initializer.setDatabasePopulator(populator);
            initializer.afterPropertiesSet();

            return dataSource;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    @DependsOn("h2WebServer")
    public Server h2EmbeddedServer()
    {
        try
        {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092", "-baseDir", "/work");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer()
    {
        try
        {
            return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}