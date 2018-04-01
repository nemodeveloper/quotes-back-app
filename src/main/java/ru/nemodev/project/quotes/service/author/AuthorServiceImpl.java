package ru.nemodev.project.quotes.service.author;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.dao.author.AuthorDAO;
import ru.nemodev.project.quotes.entity.Author;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 17.03.2018 - 14:14
 */
public class AuthorServiceImpl implements AuthorService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorDAO authorDAO;

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

    @Override
    public List<Author> getRandom(Long count)
    {
        if (count == null || count < 1)
        {
            LOGGER.warn("В сервис поиска автора передали не верный параметр count={}, поиск остановлен!",
                    count);
            return Collections.emptyList();
        }

        return authorDAO.getRandom(count);
    }
}
