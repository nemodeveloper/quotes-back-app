package ru.nemodev.project.quotes.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.repository.category.CategoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 25.03.2018 - 21:42
 */
public class CategoryServiceImpl implements CategoryService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Category> findById(Long categoryId)
    {
        if (categoryId == null || categoryId < 1L)
            return Optional.empty();

        return categoryRepository.findById(categoryId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Category> findByName(String name)
    {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAll()
    {
        return categoryRepository.findAll(Sort.by(Sort.Order.by("name")));
    }

    @Override
    @Transactional
    public Category save(Category category)
    {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public List<Category> saveAll(Iterable<Category> categoryList)
    {
        return categoryRepository.saveAll(categoryList);
    }
}
