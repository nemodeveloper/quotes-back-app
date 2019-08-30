package ru.nemodev.project.quotes.service.quote;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

@CacheConfig(cacheNames = {"quotes"})
public class QuoteCacheService implements QuoteService
{
    private final QuoteService delegate;

    public QuoteCacheService(QuoteService delegate)
    {
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

    @Override
    public Quote save(Quote quote)
    {
        return delegate.save(quote);
    }

    @Override
    public List<Quote> saveAll(Iterable<Quote> quoteList)
    {
        return delegate.saveAll(quoteList);
    }
}
