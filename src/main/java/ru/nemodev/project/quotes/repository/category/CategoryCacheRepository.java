package ru.nemodev.project.quotes.repository.category;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
// TODO КЕШ методов
@CacheConfig(cacheNames = {"categories"})
public class CategoryCacheRepository implements CategoryRepository
{
    private final CategoryRepository categoryRepository;

    public CategoryCacheRepository(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public Category getById(Long categoryId)
    {
        return categoryRepository.getById(categoryId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Category> getList()
    {
        return categoryRepository.getList();
    }

    @Override
    public Category addOrUpdate(Category category)
    {
        return categoryRepository.addOrUpdate(category);
    }

    @Override
    public List<Category> addOrUpdate(List<Category> entityList)
    {
        return categoryRepository.addOrUpdate(entityList);
    }

    @Override
    public Category getByName(String name)
    {
        return categoryRepository.getByName(name);
    }
}
