package ru.nemodev.project.quotes.dao.quote.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.nemodev.project.quotes.dao.author.jdbc.AuthorRowMapper;
import ru.nemodev.project.quotes.dao.category.jdbc.CategoryRowMapper;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by NemoDev on 17.03.2018 - 11:40
 */
public class SimpleQuoteRowMapper implements RowMapper<Quote>
{
    protected static final String ID_COLUMN = "q_id";
    protected static final String TEXT_COLUMN = "q_text";
    protected static final String SOURCE_COLUMN = "q_source";
    protected static final String YEAR_COLUMN = "q_year";

    private final AuthorRowMapper authorRowMapper;
    private final CategoryRowMapper categoryRowMapper;

    public SimpleQuoteRowMapper(AuthorRowMapper authorRowMapper, CategoryRowMapper categoryRowMapper)
    {
        this.authorRowMapper = authorRowMapper;
        this.categoryRowMapper = categoryRowMapper;
    }

    @Nullable
    @Override
    public Quote mapRow(ResultSet resultSet, int i) throws SQLException
    {
        return new Quote(
                resultSet.getLong(ID_COLUMN),
                buildCategory(resultSet, i),
                resultSet.getString(TEXT_COLUMN),
                buildAuthor(resultSet, i),
                resultSet.getString(SOURCE_COLUMN),
                resultSet.getString(YEAR_COLUMN)
        );
    }

    protected Category buildCategory(ResultSet resultSet, int i) throws SQLException
    {
        return categoryRowMapper.mapRow(resultSet, i);
    }

    protected Author buildAuthor(ResultSet resultSet, int i) throws SQLException
    {
        return authorRowMapper.mapRow(resultSet, i);
    }
}
