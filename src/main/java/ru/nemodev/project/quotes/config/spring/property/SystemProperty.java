package ru.nemodev.project.quotes.config.spring.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemProperty
{
    @Value("${system.host.name}")
    private String hostName;
    @Value("${system.static.path.base}")
    private String staticPathBase;
    @Value("${system.static.path.author}")
    private String staticPathAuthor;
    @Value("${system.static.path.category}")
    private String staticPathCategory;

    public String getHostName()
    {
        return hostName;
    }

    public String getStaticPathBase()
    {
        return staticPathBase;
    }

    public String getStaticPathAuthor()
    {
        return staticPathAuthor;
    }

    public String getStaticPathCategory()
    {
        return staticPathCategory;
    }
}
