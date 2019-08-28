package ru.nemodev.project.quotes.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.project.quotes.entity.Author;

import java.util.Optional;

/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    Optional<Author> findByFullName(String fullName);
}
