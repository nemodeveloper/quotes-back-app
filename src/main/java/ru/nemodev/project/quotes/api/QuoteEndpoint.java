package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.project.quotes.api.converter.QuoteToDTOConverter;
import ru.nemodev.project.quotes.api.dto.QuoteDTO;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.util.List;


@Controller
@RequestMapping("/v1/quote")
@ResponseBody
public class QuoteEndpoint
{
    private static final Integer MAX_LIST_COUNT = 200;

    private final QuoteService quoteService;
    private final QuoteToDTOConverter quoteToDTOConverter;

    // TODO сделать RestRequestProcessor который икапсулирует логику обработку запросов
    @Autowired
    public QuoteEndpoint(QuoteService quoteService, QuoteToDTOConverter quoteToDTOConverter)
    {
        this.quoteService = quoteService;
        this.quoteToDTOConverter = quoteToDTOConverter;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/random")
    public List<QuoteDTO> getRandom(@RequestParam("count") Integer count)
    {
        if (count == null || count < 1 || count > MAX_LIST_COUNT)
            count = MAX_LIST_COUNT;

        return quoteToDTOConverter.convertList(quoteService.getRandom(count));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/author/{authorId}")
    public List<QuoteDTO> getByAuthor(@PathVariable("authorId") Long authorId)
    {
        return quoteToDTOConverter.convertList(quoteService.getByAuthor(authorId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category/{categoryId}")
    public List<QuoteDTO> getByCategory(@PathVariable("categoryId") Long categoryId)
    {
        return quoteToDTOConverter.convertList(quoteService.getByCategory(categoryId));
    }
}
