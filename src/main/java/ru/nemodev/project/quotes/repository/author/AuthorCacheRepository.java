package ru.nemodev.project.quotes.repository.author;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
// TODO КЕШ методов
@CacheConfig(cacheNames = {"authors"})
public class AuthorCacheRepository implements AuthorRepository
{
    private final AuthorRepository authorRepository;

    public AuthorCacheRepository(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public Author getById(Long authorId)
    {
        return authorRepository.getById(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Author> getList()
    {
        return authorRepository.getList();
    }

    @Override
    public Author addOrUpdate(Author author)
    {
        return authorRepository.addOrUpdate(author);
    }

    @Override
    public List<Author> addOrUpdate(List<Author> entityList)
    {
        return authorRepository.addOrUpdate(entityList);
    }

    @Override
    public Author getByFullName(String fullName)
    {
        return authorRepository.getByFullName(fullName);
    }
}
