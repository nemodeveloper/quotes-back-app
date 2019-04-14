package ru.nemodev.project.quotes.repository.category;

import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.repository.CrudRepository;


/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Category getByName(String name);
}
