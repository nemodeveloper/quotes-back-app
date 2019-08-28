package ru.nemodev.project.quotes.repository.category;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ru.nemodev.project.quotes.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"categories"})
public class CategoryCacheRepository extends SimpleJpaRepository<Category, Long> implements CategoryRepository
{
    private final CategoryRepository delegate;

    public CategoryCacheRepository(CategoryRepository delegate, EntityManager entityManager)
    {
        super(Category.class, entityManager);
        this.delegate = delegate;
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public Optional<Category> findById(Long categoryId)
    {
        return super.findById(categoryId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Category> findAll()
    {
        return super.findAll();
    }

    @Override
    public Optional<Category> findByName(String name)
    {
        return delegate.findByName(name);
    }
}
