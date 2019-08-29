package ru.nemodev.project.quotes.config.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * created by sbrf-simanov-an on 21.11.2018 - 13:48
 */
@Configuration
@PropertySource(value = "classpath:config/database.yml", factory = YamlPropertyLoaderFactory.class)
public class DataBaseProperty
{
    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.socket.timeout}")
    private int socketTimeout;

    public String getDriver()
    {
        return driver;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public int getSocketTimeout()
    {
        return socketTimeout;
    }
}