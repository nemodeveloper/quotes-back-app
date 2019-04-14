package ru.nemodev.project.quotes.service.quote;

import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:22
 */
public interface QuoteService
{
    List<Quote> getRandom(Integer count);

    List<Quote> getByAuthor(Long authorId);
    List<Quote> getByCategory(Long categoryId);

    Quote addOrUpdate(Quote quote);
    List<Quote> addOrUpdate(List<Quote> quoteList);
}
