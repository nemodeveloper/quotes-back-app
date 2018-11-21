package ru.nemodev.project.quotes.dao.quote.jdbc;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.nemodev.project.quotes.dao.AbstractSpringJdbc;
import ru.nemodev.project.quotes.dao.quote.QuoteDAO;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 15.03.2018 - 23:51
 */
public class QuoteJdbcDAO extends AbstractSpringJdbc implements QuoteDAO
{
    // TODO Доработать запросы к БД, вынести их в отдельный класс
    private static final String RANDOM_QUOTE_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year, q.author_id a_id, q.category_id c_id, " +
            "c.name c_name, " +
            "a.full_name a_full_name FROM quote q\n" +
            "  LEFT JOIN category c ON q.category_id = c.id\n" +
            "  LEFT JOIN author a ON q.author_id = a.id\n" +
            "  ORDER BY RANDOM()" +
            "  LIMIT :count_row";

    private static final String AUTHOR_ID_PARAM_KEY = "author_id";
    private static final String QUOTE_BY_AUTHOR_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year,\n" +
                    "q.category_id c_id, c.name c_name\n" +
                    "FROM quote q\n" +
                    "  LEFT JOIN category c ON q.category_id = c.id\n" +
                    "   WHERE q.author_id = :author_id\n";

    private static final String CATEGORY_ID_PARAM_KEY = "category_id";
    private static final String QUOTE_BY_CATEGORY_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year,\n" +
                    "q.author_id a_id, a.full_name a_full_name\n" +
                    "FROM quote q\n" +
                    "  LEFT JOIN author a ON q.author_id = a.id\n" +
                    "   WHERE q.category_id = :category_id";

    private final SimpleQuoteRowMapper simpleQuoteRowMapper;
    private final ObjectFactory<PrepareQuoteRowMapper> prepareQuoteRowMapper;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public QuoteJdbcDAO(NamedParameterJdbcOperations jdbcOperations,
                        SimpleQuoteRowMapper simpleQuoteRowMapper,
                        ObjectFactory<PrepareQuoteRowMapper> prepareQuoteRowMapper,
                        AuthorService authorService,
                        CategoryService categoryService)
    {
        super(jdbcOperations);
        this.simpleQuoteRowMapper = simpleQuoteRowMapper;
        this.prepareQuoteRowMapper = prepareQuoteRowMapper;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Quote> getRandom(Long count)
    {
        return jdbcOperations.query(RANDOM_QUOTE_QUERY,
                new MapSqlParameterSource(COUNT_ROW_PARAM_KEY, count),
                simpleQuoteRowMapper);
    }

    @Override
    public List<Quote> getByAuthor(Long authorId)
    {
        Author author = authorService.getById(authorId);
        if (author == null)
            return Collections.emptyList();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(AUTHOR_ID_PARAM_KEY, authorId);

        PrepareQuoteRowMapper rowMapper = prepareQuoteRowMapper.getObject();
        rowMapper.setAuthor(author);

        return jdbcOperations.query(QUOTE_BY_AUTHOR_QUERY,
                parameterSource,
                rowMapper);
    }

    @Override
    public List<Quote> getByCategory(Long categoryId)
    {
        Category category = categoryService.getById(categoryId);
        if (category == null)
            return Collections.emptyList();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CATEGORY_ID_PARAM_KEY, categoryId);

        PrepareQuoteRowMapper rowMapper = prepareQuoteRowMapper.getObject();
        rowMapper.setCategory(category);

        return jdbcOperations.query(QUOTE_BY_CATEGORY_QUERY,
                parameterSource,
                rowMapper);
    }
}
