package ru.nemodev.project.quotes.service.category;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.dao.category.CategoryDAO;
import ru.nemodev.project.quotes.entity.Category;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 25.03.2018 - 21:42
 */
public class CategoryServiceImpl implements CategoryService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO)
    {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category getByName(String name)
    {
        if (StringUtils.isBlank(name))
        {
            LOGGER.warn("В сервис поиска категории передали пустое название, поиск остановлен!");
            return null;
        }
        return categoryDAO.getByTitle(name);
    }

    @Override
    public List<Category> getRandom(Long count)
    {
        if (count == null || count < 1)
        {
            LOGGER.warn("В сервис поиска категории передали не верный параметр count={}, поиск остановлен!",
                    count);
            return Collections.emptyList();
        }

        return null;
    }
}
