package ru.nemodev.project.quotes.service.author;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.utils.SortUtils;

import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"authors"})
public class AuthorCacheService implements AuthorService
{
    private final AuthorService delegate;

    public AuthorCacheService(AuthorService delegate)
    {
        this.delegate = delegate;
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public Optional<Author> findById(Long authorId)
    {
        return delegate.findById(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Author> findAll()
    {
        List<Author> result = delegate.findAll();
        SortUtils.sortRusString(result, Author::getFullName);
        return result;
    }

    @Override
    public Author save(Author author)
    {
        return delegate.save(author);
    }

    @Override
    public List<Author> saveAll(Iterable<Author> authorList)
    {
        return delegate.saveAll(authorList);
    }

    @Override
    @Cacheable(key = "#root.method.name + #fullName", sync = true)
    public Optional<Author> findByFullName(String fullName)
    {
        return delegate.findByFullName(fullName);
    }
}
