package ru.nemodev.project.quotes.repository.author;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ru.nemodev.project.quotes.entity.Author;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * created by NemoDev on 19.07.2018 - 13:08
 */
@CacheConfig(cacheNames = {"authors"})
public class AuthorCacheRepository extends SimpleJpaRepository<Author, Long> implements AuthorRepository
{
    private final AuthorRepository delegate;

    public AuthorCacheRepository(AuthorRepository delegate, EntityManager entityManager)
    {
        super(Author.class, entityManager);
        this.delegate = delegate;
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public Optional<Author> findById(Long authorId)
    {
        return super.findById(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name", sync = true)
    public List<Author> findAll()
    {
        return super.findAll();
    }

    @Override
    public Optional<Author> findByFullName(String fullName)
    {
        return delegate.findByFullName(fullName);
    }
}
