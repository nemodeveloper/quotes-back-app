package ru.nemodev.project.quotes.dao.quote;

import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:45
 */
public interface QuoteDAO
{
    /**
     *
     * @param count
     * @return
     */
    List<Quote> getRandom(Long count);

    /**
     *
     * @param authorId
     * @return
     */
    List<Quote> getByAuthor(Long authorId);

    /**
     *
     * @param categoryId
     * @return
     */
    List<Quote> getByCategory(Long categoryId);
}
