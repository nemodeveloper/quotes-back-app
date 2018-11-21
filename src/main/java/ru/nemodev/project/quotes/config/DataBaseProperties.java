package ru.nemodev.project.quotes.config;

import java.util.Properties;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 13:48
 */
public class DataBaseProperties
{
    private final static String DRIVER_KEY = "db.driver";
    private final static String URL_KEY = "db.url";
    private final static String USERNAME_KEY = "db.username";
    private final static String PASSWORD_KEY = "db.password";
    private final static String SOCKET_TIMEOUT_KEY = "db.socket.timeout";

    private final String driver;
    private final String URL;

    private final String username;
    private final String password;

    private final int socketTimeout;

    public DataBaseProperties(Properties properties)
    {
        this.driver = properties.getProperty(DRIVER_KEY);
        this.URL = properties.getProperty(URL_KEY);
        this.username = properties.getProperty(USERNAME_KEY);
        this.password = properties.getProperty(PASSWORD_KEY);
        this.socketTimeout = Integer.parseInt(properties.getProperty(SOCKET_TIMEOUT_KEY));
    }

    public String getDriver()
    {
        return driver;
    }

    public String getURL()
    {
        return URL;
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
