package ru.nemodev.project.quotes.service.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.dao.quote.QuoteDAO;
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
    public List<Quote> getByAuthor(Long authorId)
    {
        if (authorId == null || authorId < 1)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры authorId={}, поиск остановлен!",
                    authorId);
            return Collections.emptyList();
        }
        return quoteDAO.getByAuthor(authorId);
    }

    @Override
    public List<Quote> getByCategory(Long categoryId)
    {
        if (categoryId == null || categoryId < 1)
        {
            LOGGER.warn("В сервис поиска цитат передали не корректные параметры categoryId={}, поиск остановлен!",
                    categoryId);
            return Collections.emptyList();
        }

        return quoteDAO.getByCategory(categoryId);
    }
}
