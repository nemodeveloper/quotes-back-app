package ru.nemodev.project.quotes.service.category;

import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:23
 */
public interface CategoryService
{

    Category getById(Long categoryId);

    List<Category> getList();

    /**
     *
     * @param name
     * @return
     */
    Category getByName(String name);

}
