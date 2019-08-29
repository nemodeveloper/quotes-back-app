package ru.nemodev.project.quotes.config.database;

import javax.sql.DataSource;


/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:18
 */
public interface DataSourceHolder
{
    DataSource dataSource();
}
