package ru.nemodev.project.quotes.service.category;

import ru.nemodev.project.quotes.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 13.03.2018 - 21:23
 */
public interface CategoryService
{
    Optional<Category> findById(Long categoryId);
    Optional<Category> findByName(String name);

    List<Category> findAll();

    Category save(Category category);
    List<Category> saveAll(Iterable<Category> categoryList);
}
