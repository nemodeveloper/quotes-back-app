package ru.nemodev.project.quotes.repository.author;

import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.repository.CrudRepository;

/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
public interface AuthorRepository extends CrudRepository<Author, Long>
{
    Author getByFullName(String fullName);
}
