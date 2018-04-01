package ru.nemodev.project.quotes.dao.category.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.nemodev.project.quotes.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by NemoDev on 17.03.2018 - 11:43
 */
public class CategoryRowMapper implements RowMapper<Category>
{
    private static final String ID_COLUMN = "c_id";
    private static final String NAME_COLUMN = "c_name";

    @Nullable
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException
    {
        return new Category(
                resultSet.getLong(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );

    }
}
