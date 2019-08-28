package ru.nemodev.project.quotes.service.quote;

import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:22
 */
public interface QuoteService
{
    List<Quote> getRandom(Integer count);

    List<Quote> findByAuthor(Long authorId);
    List<Quote> findByCategory(Long categoryId);

    Quote save(Quote quote);
    List<Quote> saveAll(Iterable<Quote> quoteList);
}
