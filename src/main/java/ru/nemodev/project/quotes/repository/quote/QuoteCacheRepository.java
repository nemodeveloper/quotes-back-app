package ru.nemodev.project.quotes.repository.quote;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

// TODO КЕШ методов
@CacheConfig(cacheNames = {"quotes"})
public class QuoteCacheRepository implements QuoteRepository
{
    private final QuoteRepository quoteRepository;

    public QuoteCacheRepository(QuoteRepository quoteRepository)
    {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return quoteRepository.getRandom(count);
    }

    @Override
    @Cacheable(key = "#root.method.name + #authorId", sync = true)
    public List<Quote> getByAuthor(Long authorId)
    {
        return quoteRepository.getByAuthor(authorId);
    }

    @Override
    @Cacheable(key = "#root.method.name + #categoryId", sync = true)
    public List<Quote> getByCategory(Long categoryId)
    {
        return quoteRepository.getByCategory(categoryId);
    }

    @Override
    public Quote getById(Long id)
    {
        return quoteRepository.getById(id);
    }

    @Override
    public List<Quote> getList()
    {
        return quoteRepository.getList();
    }

    @Override
    public Quote addOrUpdate(Quote quote)
    {
        return quoteRepository.addOrUpdate(quote);
    }

    @Override
    public List<Quote> addOrUpdate(List<Quote> entityList)
    {
        return quoteRepository.addOrUpdate(entityList);
    }
}
