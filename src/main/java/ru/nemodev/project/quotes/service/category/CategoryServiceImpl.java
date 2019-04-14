package ru.nemodev.project.quotes.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.repository.category.CategoryRepository;

import java.util.List;

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
    public Category getById(Long categoryId)
    {
        if (categoryId == null || categoryId < 1L)
            return null;

        return categoryRepository.getById(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getByName(String name)
    {
        return categoryRepository.getByName(name);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> getList()
    {
        return categoryRepository.getList();
    }

    @Override
    @Transactional
    public Category addOrUpdate(Category category)
    {
        return categoryRepository.addOrUpdate(category);
    }

    @Override
    @Transactional
    public List<Category> addOrUpdate(List<Category> categoryList)
    {
        return categoryRepository.addOrUpdate(categoryList);
    }
}
