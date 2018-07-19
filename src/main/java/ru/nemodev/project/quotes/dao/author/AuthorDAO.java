package ru.nemodev.project.quotes.dao.author;

import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
public interface AuthorDAO
{
    Author getById(Long authorId);

    List<Author> getList();

    /**
     *
     * @param name
     * @return
     */
    Author getByName(String name);

}
