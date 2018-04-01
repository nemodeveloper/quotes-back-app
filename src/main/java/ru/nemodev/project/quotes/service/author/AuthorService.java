package ru.nemodev.project.quotes.service.author;

import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:23
 */
public interface AuthorService
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
