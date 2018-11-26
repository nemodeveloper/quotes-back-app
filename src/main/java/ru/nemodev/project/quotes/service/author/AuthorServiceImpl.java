package ru.nemodev.project.quotes.service.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.dao.author.AuthorDAO;
import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 17.03.2018 - 14:14
 */
public class AuthorServiceImpl implements AuthorService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO)
    {
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Long authorId)
    {
        if (authorId == null || authorId < 1L)
            return null;

        return authorDAO.getById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getList()
    {
        return authorDAO.getList();
    }
}
