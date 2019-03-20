package ru.nemodev.project.quotes.config;

import java.util.Properties;

public class SystemProperties
{
    private static final String HOST_NAME_KEY = "host.name";

    private static final String STATIC_PATH_BASE_KEY = "static.path.base";
    private static final String STATIC_PATH_AUTHOR_KEY = "static.path.author";
    private static final String STATIC_PATH_CATEGORY_KEY = "static.path.category";

    private final String hostName;
    private final String staticPathBase;
    private final String staticPathAuthor;
    private final String staticPathCategory;

    public SystemProperties(Properties properties)
    {
        this.hostName = properties.getProperty(HOST_NAME_KEY);
        this.staticPathBase = properties.getProperty(STATIC_PATH_BASE_KEY);
        this.staticPathAuthor = properties.getProperty(STATIC_PATH_AUTHOR_KEY);
        this.staticPathCategory = properties.getProperty(STATIC_PATH_CATEGORY_KEY);
    }

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
