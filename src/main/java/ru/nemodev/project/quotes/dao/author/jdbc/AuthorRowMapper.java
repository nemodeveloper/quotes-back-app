package ru.nemodev.project.quotes.dao.author.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.nemodev.project.quotes.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by NemoDev on 17.03.2018 - 11:44
 */
public class AuthorRowMapper implements RowMapper<Author>
{
    private static final String ID_COLUMN = "a_id";
    private static final String FULL_NAME_COLUMN = "a_full_name";

    @Nullable
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException
    {
        if (resultSet.getLong(ID_COLUMN) == 0L)
            return null;

        return new Author(
                resultSet.getLong(ID_COLUMN),
                resultSet.getString(FULL_NAME_COLUMN)
        );
    }
}
