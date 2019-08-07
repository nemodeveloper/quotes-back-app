package ru.nemodev.project.quotes.service.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.repository.author.AuthorRepository;

import java.util.List;

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
    public Author getById(Long authorId)
    {
        if (authorId == null || authorId < 1L)
            return null;

        return authorRepository.getById(authorId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Author getByFullName(String fullName)
    {
        return authorRepository.getByFullName(fullName);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Author> getList()
    {
        return authorRepository.getList();
    }

    @Override
    @Transactional
    public Author addOrUpdate(Author author)
    {
        return authorRepository.addOrUpdate(author);
    }

    @Override
    @Transactional
    public List<Author> addOrUpdate(List<Author> authorList)
    {
        return authorRepository.addOrUpdate(authorList);
    }
}
