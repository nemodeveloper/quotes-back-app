package ru.nemodev.project.quotes.service.author;

import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:23
 */
public interface AuthorService
{
    Author getById(Long authorId);

    List<Author> getList();

    Author addOrUpdate(Author author);
}
