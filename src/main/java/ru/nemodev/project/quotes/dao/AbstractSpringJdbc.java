package ru.nemodev.project.quotes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

/**
 * created by NemoDev on 17.03.2018 - 14:18
 */
public abstract class AbstractSpringJdbc
{
    protected static final String COUNT_ROW_PARAM_KEY = "count_row";

    @Autowired
    protected NamedParameterJdbcOperations jdbcOperations;
}