package ru.nemodev.project.quotes.dao.category;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"categories"})
public class CategoryCacheDAO implements CategoryDAO
{
    private final CategoryDAO categoryDAO;

    public CategoryCacheDAO(CategoryDAO categoryDAO)
    {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public Category getById(Long categoryId)
    {
        return categoryDAO.getById(categoryId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Category> getList()
    {
        return categoryDAO.getList();
    }

    @Override
    public Category addOrUpdate(Category category)
    {
        return categoryDAO.addOrUpdate(category);
    }
}
