package ru.nemodev.project.quotes.config.spring.database;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:18
 */
public interface DataBaseSource
{
    DataSource dataSource();
    Properties hibernateProperties();
}
