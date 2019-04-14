package ru.nemodev.project.quotes.repository.quote;

import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.repository.CrudRepository;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:45
 */
public interface QuoteRepository extends CrudRepository<Quote, Long>
{
    List<Quote> getRandom(Integer count);

    List<Quote> getByAuthor(Long authorId);
    List<Quote> getByCategory(Long categoryId);
}
