package ru.nemodev.project.quotes.repository.quote;

import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:45
 */
public interface QuoteOptionalRepository
{
    List<Quote> getRandom(Integer count);
}
