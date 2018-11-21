package ru.nemodev.project.quotes.config.spring.database;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:18
 */
public interface DataBaseSource
{
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();
}
