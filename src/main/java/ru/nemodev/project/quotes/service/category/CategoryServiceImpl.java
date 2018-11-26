package ru.nemodev.project.quotes.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.dao.category.CategoryDAO;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 25.03.2018 - 21:42
 */
public class CategoryServiceImpl implements CategoryService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO)
    {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long categoryId)
    {
        if (categoryId == null || categoryId < 1L)
            return null;

        return categoryDAO.getById(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getList()
    {
        return categoryDAO.getList();
    }
}
