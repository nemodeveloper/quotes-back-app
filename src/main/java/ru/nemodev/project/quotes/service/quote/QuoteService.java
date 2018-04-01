package ru.nemodev.project.quotes.service.quote;

import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
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
     * @param author
     * @param count
     * @return
     */
    List<Quote> getRandomByAuthor(Author author, Long count);

    /**
     *
     * @param category
     * @param count
     * @return
     */
    List<Quote> getRandomByCategory(Category category, Long count);

}
