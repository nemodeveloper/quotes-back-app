package ru.nemodev.project.quotes.service.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.dao.quote.QuoteDAO;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 15.03.2018 - 23:47
 */
public class QuoteServiceImpl implements QuoteService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteServiceImpl.class);

    private final QuoteDAO quoteDAO;

    @Autowired
    public QuoteServiceImpl(QuoteDAO quoteDAO)
    {
        this.quoteDAO = quoteDAO;
    }

    @Override
    public List<Quote> getRandom(Long count)
    {
        if (count == null || count < 1L)
        {
            LOGGER.warn("В сервис поиска случайных цитат передали параметр count={}, поиск остановлен!", count);
            return Collections.emptyList();
        }
        return quoteDAO.getRandom(count);
    }

    @Override
    public List<Quote> getRandomByAuthor(Author author, Long count)
    {
        if (author == null || count == null || count < 1)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры author={} count={}, поиск остановлен!",
                    author, count);
            return Collections.emptyList();
        }
        return quoteDAO.getRandomByAuthor(author, count);
    }

    @Override
    public List<Quote> getRandomByCategory(Category category, Long count)
    {
        if (category == null || count == null || count < 1)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры category={} count={}, поиск остановлен!",
                    category, count);
            return Collections.emptyList();
        }

        return quoteDAO.getRandomByCategory(category, count);
    }
}
