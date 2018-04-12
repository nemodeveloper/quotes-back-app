package ru.nemodev.project.quotes.dao.category.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.nemodev.project.quotes.dao.AbstractSpringJdbc;
import ru.nemodev.project.quotes.dao.category.CategoryDAO;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 25.03.2018 - 21:37
 */
public class CategoryJdbcDAO extends AbstractSpringJdbc implements CategoryDAO
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryJdbcDAO.class);

    private static final String CATEGORY_NAME_PARAM_KEY = "category_name";
    private static final String CATEGORY_BY_NAME_QUERY =
            "SELECT c.id c_id, c.name c_name FROM category c\n" +
                    "WHERE lower(c.name) LIKE '%' || :category_name || '%'\n" +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT 1";

    private static final String CATEGORY_BY_RANDOM_QUERY =
            "SELECT c.id c_id, c.name c_name FROM category c\n" +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT :count_row";

    private final CategoryRowMapper rowMapper;

    @Autowired
    public CategoryJdbcDAO(NamedParameterJdbcOperations jdbcOperations, CategoryRowMapper rowMapper)
    {
        super(jdbcOperations);
        this.rowMapper = rowMapper;
    }

    @Override
    public Category getByTitle(String name)
    {
        try
        {
            return jdbcOperations.queryForObject(CATEGORY_BY_NAME_QUERY,
                    new MapSqlParameterSource(CATEGORY_NAME_PARAM_KEY, name),
                    rowMapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            LOGGER.info("Не удалось найти категорию цитаты по шаблону - {}", name);
        }
        catch (Exception e)
        {
            LOGGER.error("Ошибка при поиске категории по шаблону - {}", name, e);
        }

        return null;
    }

    @Override
    public List<Category> getRandom(Long count)
    {
        return jdbcOperations.query(CATEGORY_BY_RANDOM_QUERY,
                new MapSqlParameterSource(COUNT_ROW_PARAM_KEY, count),
                rowMapper);
    }
}
