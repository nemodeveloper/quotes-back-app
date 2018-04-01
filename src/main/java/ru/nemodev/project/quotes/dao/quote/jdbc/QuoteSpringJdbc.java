package ru.nemodev.project.quotes.dao.quote.jdbc;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import ru.nemodev.project.quotes.dao.AbstractSpringJdbc;
import ru.nemodev.project.quotes.dao.quote.QuoteDAO;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 15.03.2018 - 23:51
 */
public class QuoteSpringJdbc extends AbstractSpringJdbc implements QuoteDAO
{
    // TODO Доработать запросы к БД, вынести их в отдельный класс
    private static final String RANDOM_QUOTE_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year, q.author_id a_id, q.category_id c_id, " +
            "c.name c_name, " +
            "a.full_name a_full_name FROM quote q\n" +
            "  LEFT JOIN category c ON q.category_id = c.id\n" +
            "  LEFT JOIN author a ON q.author_id = a.id\n" +
            "  WHERE q.id = FLOOR((\n" +
                    "  SELECT reltuples::bigint * random() AS count from pg_class\n" +
                    "    WHERE oid = 'public.quote'::regclass\n" +
                    "  ))" +
            " LIMIT :count_row";

    private static final String AUTHOR_ID_PARAM_KEY = "author_id";
    private static final String RANDOM_QUOTE_BY_AUTHOR_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year,\n" +
                    "q.category_id c_id, c.name c_name\n" +
                    "FROM quote q\n" +
                    "  LEFT JOIN category c ON q.category_id = c.id\n" +
                    "   WHERE q.author_id = :author_id\n" +
                    "  ORDER BY RANDOM()\n" +
                    "LIMIT :count_row";

    private static final String CATEGORY_ID_PARAM_KEY = "category_id";
    private static final String RANDOM_QUOTE_BY_CATEGORY_QUERY =
            "SELECT q.id q_id, q.text q_text, q.source q_source, q.year q_year,\n" +
                    "q.author_id a_id, a.full_name a_full_name\n" +
                    "FROM quote q\n" +
                    "  LEFT JOIN author a ON q.author_id = a.id\n" +
                    "   WHERE q.category_id = :category_id\n" +
                    "  ORDER BY RANDOM()\n" +
                    "LIMIT :count_row";

    private final SimpleQuoteRowMapper simpleQuoteRowMapper;
    private final ObjectFactory<PrepareQuoteRowMapper> prepareQuoteRowMapper;

    @Autowired
    public QuoteSpringJdbc(
            @Qualifier("simpleQuoteRowMapper") SimpleQuoteRowMapper simpleQuoteRowMapper,
            ObjectFactory<PrepareQuoteRowMapper> prepareQuoteRowMapper)
    {
        this.simpleQuoteRowMapper = simpleQuoteRowMapper;
        this.prepareQuoteRowMapper = prepareQuoteRowMapper;
    }

    @Override
    public List<Quote> getRandom(Long count)
    {
        return jdbcOperations.query(RANDOM_QUOTE_QUERY,
                new MapSqlParameterSource(COUNT_ROW_PARAM_KEY, count),
                simpleQuoteRowMapper);
    }

    @Override
    public List<Quote> getRandomByAuthor(Author author, Long count)
    {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(AUTHOR_ID_PARAM_KEY, author.getId());
        parameterSource.addValue(COUNT_ROW_PARAM_KEY, count);

        PrepareQuoteRowMapper rowMapper = prepareQuoteRowMapper.getObject();
        rowMapper.setAuthor(author);

        return jdbcOperations.query(RANDOM_QUOTE_BY_AUTHOR_QUERY,
                parameterSource,
                rowMapper);
    }

    @Override
    public List<Quote> getRandomByCategory(Category category, Long count)
    {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CATEGORY_ID_PARAM_KEY, category.getId());
        parameterSource.addValue(COUNT_ROW_PARAM_KEY, count);

        PrepareQuoteRowMapper rowMapper = prepareQuoteRowMapper.getObject();
        rowMapper.setCategory(category);

        return jdbcOperations.query(RANDOM_QUOTE_BY_CATEGORY_QUERY,
                parameterSource,
                rowMapper);
    }
}
