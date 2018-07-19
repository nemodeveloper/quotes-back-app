package ru.nemodev.project.quotes.service.quote;

import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:22
 */
public interface QuoteService
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
