package ru.nemodev.project.quotes.service.category;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.utils.SortUtils;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"categories"})
public class CategoryCacheService implements CategoryService
{
    private final CategoryService delegate;

    public CategoryCacheService(CategoryService delegate)
    {
        this.delegate = delegate;
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public Optional<Category> findById(Long categoryId)
    {
        return delegate.findById(categoryId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Category> findAll()
    {
        List<Category> result = delegate.findAll();
        SortUtils.sortRusString(result, Category::getName);
        return result;
    }

    @Override
    public Category save(Category category)
    {
        return delegate.save(category);
    }

    @Override
    public List<Category> saveAll(Iterable<Category> categoryList)
    {
        return delegate.saveAll(categoryList);
    }

    @Override
    @Cacheable(key = "#root.method.name + #name", sync = true)
    public Optional<Category> findByName(String name)
    {
        return delegate.findByName(name);
    }
}
