package ru.nemodev.project.quotes.dao.author;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"authors"})
public class AuthorCacheDAO implements AuthorDAO
{
    private final AuthorDAO authorDAO;

    public AuthorCacheDAO(AuthorDAO authorDAO)
    {
        this.authorDAO = authorDAO;
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public Author getById(Long authorId)
    {
        return authorDAO.getById(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Author> getList()
    {
        return authorDAO.getList();
    }

    @Override
    @Cacheable(key = "#root.method.name + #name", sync = true)
    public Author getByName(String name)
    {
        return authorDAO.getByName(name);
    }
}
