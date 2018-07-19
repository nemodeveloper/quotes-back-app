package ru.nemodev.project.quotes.service.author;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Author getById(Long authorId)
    {
        if (authorId == null || authorId < 1L)
            return null;

        return authorDAO.getById(authorId);
    }

    @Override
    public List<Author> getList() {
        return authorDAO.getList();
    }

    @Override
    public Author getByName(String name)
    {
        if (StringUtils.isBlank(name))
        {
            LOGGER.warn("В сервис поиска автора передали пустое имя, поиск остановлен!");
            return null;
        }

        return authorDAO.getByName(name);
    }

}
