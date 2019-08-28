package ru.nemodev.project.quotes.service.author;

import ru.nemodev.project.quotes.entity.Author;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 13.03.2018 - 21:23
 */
public interface AuthorService
{
    Optional<Author> findById(Long authorId);
    Optional<Author> findByFullName(String fullName);

    List<Author> findAll();

    Author save(Author author);
    List<Author> saveAll(Iterable<Author> authorList);
}
