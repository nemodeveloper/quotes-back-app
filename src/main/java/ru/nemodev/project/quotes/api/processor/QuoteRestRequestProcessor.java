package ru.nemodev.project.quotes.api.processor;

import ru.nemodev.project.quotes.api.converter.QuoteToDTOConverter;
import ru.nemodev.project.quotes.api.dto.QuoteDTO;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.util.List;

public class QuoteRestRequestProcessor
{
    private static final Integer DEFAULT_LIST_COUNT = 100;
    private static final Integer MAX_LIST_COUNT = 200;

    private final QuoteService quoteService;
    private final QuoteToDTOConverter quoteToDTOConverter;

    public QuoteRestRequestProcessor(QuoteService quoteService, QuoteToDTOConverter quoteToDTOConverter)
    {
        this.quoteService = quoteService;
        this.quoteToDTOConverter = quoteToDTOConverter;
    }

    public List<QuoteDTO> getRandom(Integer count)
    {
        if (count == null || count < 1)
            count = DEFAULT_LIST_COUNT;
        else if (count > MAX_LIST_COUNT)
            count = MAX_LIST_COUNT;

        return quoteToDTOConverter.convertList(quoteService.getRandom(count));
    }

    public List<QuoteDTO> getByAuthor(Long authorId)
    {
        return quoteToDTOConverter.convertList(quoteService.getByAuthor(authorId));
    }

    public List<QuoteDTO> getByCategory(Long categoryId)
    {
        return quoteToDTOConverter.convertList(quoteService.getByCategory(categoryId));
    }
}
