package ru.nemodev.project.quotes.dao.category;

import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
public interface CategoryDAO
{
    /**
     *
     * @param name
     * @return
     */
    Category getByTitle(String name);

    /**
     *
     * @param count
     * @return
     */
    List<Category> getRandom(Long count);

}
