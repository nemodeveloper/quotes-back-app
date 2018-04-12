package ru.nemodev.project.quotes.dao.author.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.nemodev.project.quotes.dao.AbstractSpringJdbc;
import ru.nemodev.project.quotes.dao.author.AuthorDAO;
import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 17.03.2018 - 14:16
 */
public class AuthorJdbcDAO extends AbstractSpringJdbc implements AuthorDAO
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorJdbcDAO.class);

    private static final String AUTHOR_NAME_PARAM_KEY = "author_name";
    private static final String AUTHOR_BY_NAME_QUERY =
            "SELECT a.id a_id, a.full_name a_full_name FROM author a\n" +
                    "WHERE LOWER(a.full_name) LIKE '%' || :author_name || '%'\n" +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT 1";

    private static final String AUTHOR_BY_RANDOM_QUERY =
            "SELECT a.id a_id, a.full_name a_full_name FROM author a\n" +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT :count_row";

    private final AuthorRowMapper rowMapper;

    @Autowired
    public AuthorJdbcDAO(NamedParameterJdbcOperations jdbcOperations, AuthorRowMapper rowMapper)
    {
        super(jdbcOperations);
        this.rowMapper = rowMapper;
    }

    @Override
    public Author getByName(String name)
    {
        try
        {
            return jdbcOperations.queryForObject(AUTHOR_BY_NAME_QUERY,
                    new MapSqlParameterSource(AUTHOR_NAME_PARAM_KEY, name),
                    rowMapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            LOGGER.info("Не удалось найти автора цитаты с именем - {}", name);
        }
        catch (Exception e)
        {
            LOGGER.error("Ошибка при поиске автора с именем - {}", name, e);
        }
        return null;
    }

    @Override
    public List<Author> getRandom(Long count)
    {
        return jdbcOperations.query(AUTHOR_BY_RANDOM_QUERY,
                new MapSqlParameterSource(COUNT_ROW_PARAM_KEY, count),
                rowMapper);
    }
}
