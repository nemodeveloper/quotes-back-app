package ru.nemodev.project.quotes.repository.quote;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ru.nemodev.project.quotes.entity.Quote;

import javax.persistence.EntityManager;
import java.util.List;

@CacheConfig(cacheNames = {"quotes"})
public class QuoteCacheRepository extends SimpleJpaRepository<Quote, Long> implements QuoteRepository
{
    private final QuoteRepository delegate;

    public QuoteCacheRepository(QuoteRepository delegate, EntityManager entityManager)
    {
        super(Quote.class, entityManager);
        this.delegate = delegate;
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return delegate.getRandom(count);
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public List<Quote> findByAuthor(Long authorId)
    {
        return delegate.findByAuthor(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public List<Quote> findByCategory(Long categoryId)
    {
        return delegate.findByCategory(categoryId);
    }
}
