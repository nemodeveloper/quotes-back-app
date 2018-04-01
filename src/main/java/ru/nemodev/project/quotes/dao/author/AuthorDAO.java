package ru.nemodev.project.quotes.dao.author;

import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
public interface AuthorDAO
{
    /**
     *
     * @param name
     * @return
     */
    Author getByName(String name);

    /**
     *
     * @param count
     * @return
     */
    List<Author> getRandom(Long count);
}
