package ru.nemodev.project.quotes.dao.quote.jdbc;

import ru.nemodev.project.quotes.dao.category.jdbc.CategoryRowMapper;
import ru.nemodev.project.quotes.dao.author.jdbc.AuthorRowMapper;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by NemoDev on 17.03.2018 - 14:01
 */
public class PrepareQuoteRowMapper extends SimpleQuoteRowMapper
{
    private Author author;
    private Category category;

    public PrepareQuoteRowMapper(AuthorRowMapper authorRowMapper, CategoryRowMapper categoryRowMapper)
    {
        super(authorRowMapper, categoryRowMapper);
    }

    @Override
    protected Category buildCategory(ResultSet resultSet, int i) throws SQLException
    {
        if (category != null)
            return category;

        return super.buildCategory(resultSet, i);
    }

    @Override
    protected Author buildAuthor(ResultSet resultSet, int i) throws SQLException
    {
        if (author != null)
            return author;

        return super.buildAuthor(resultSet, i);
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

}
