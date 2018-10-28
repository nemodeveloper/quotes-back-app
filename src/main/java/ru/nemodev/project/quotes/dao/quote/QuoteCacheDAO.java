package ru.nemodev.project.quotes.dao.quote;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

@CacheConfig(cacheNames = {"quotes"})
public class QuoteCacheDAO implements QuoteDAO
{
    private final QuoteDAO quoteDAO;

    public QuoteCacheDAO(QuoteDAO quoteDAO)
    {
        this.quoteDAO = quoteDAO;
    }

    @Override
    public List<Quote> getRandom(Long count)
    {
        return quoteDAO.getRandom(count);
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public List<Quote> getByAuthor(Long authorId)
    {
        return quoteDAO.getByAuthor(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public List<Quote> getByCategory(Long categoryId)
    {
        return quoteDAO.getByCategory(categoryId);
    }
}
