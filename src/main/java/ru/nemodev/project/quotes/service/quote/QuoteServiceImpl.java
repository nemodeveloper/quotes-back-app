package ru.nemodev.project.quotes.service.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.repository.quote.QuoteRepository;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 15.03.2018 - 23:47
 */
public class QuoteServiceImpl implements QuoteService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteServiceImpl.class);

    private final QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository)
    {
        this.quoteRepository = quoteRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Quote> getRandom(Integer count)
    {
        if (count == null || count < 1L)
        {
            LOGGER.warn("В сервис поиска случайных цитат передали не корректный параметр count={}, поиск остановлен!", count);
            return Collections.emptyList();
        }
        return quoteRepository.getRandom(count);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Quote> findByAuthor(Long authorId)
    {
        if (authorId == null)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры authorId={}, поиск остановлен!", authorId);
            return Collections.emptyList();
        }
        return quoteRepository.findByAuthor(authorId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Quote> findByCategory(Long categoryId)
    {
        if (categoryId == null)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры categoryId={}, поиск остановлен!", categoryId);
            return Collections.emptyList();
        }

        return quoteRepository.findByCategory(categoryId);
    }

    @Override
    @Transactional
    public Quote save(Quote quote)
    {
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public List<Quote> saveAll(Iterable<Quote> quoteList)
    {
        return quoteRepository.saveAll(quoteList);
    }
}
