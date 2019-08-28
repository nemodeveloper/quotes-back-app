package ru.nemodev.project.quotes.service.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.repository.author.AuthorRepository;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 17.03.2018 - 14:14
 */
public class AuthorServiceImpl implements AuthorService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Author> findById(Long authorId)
    {
        if (authorId == null || authorId < 1L)
            return Optional.empty();

        return authorRepository.findById(authorId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Author> findByFullName(String fullName)
    {
        return authorRepository.findByFullName(fullName);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Author> findAll()
    {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author)
    {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public List<Author> saveAll(Iterable<Author> authorList)
    {
        return authorRepository.saveAll(authorList);
    }
}
