package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.util.List;


@Controller
@RequestMapping("/v1/quote")
@ResponseBody
public class QuoteEndpoint
{
    private static final Integer MAX_LIST_COUNT = 200;

    private final QuoteService quoteService;

    @Autowired
    public QuoteEndpoint(QuoteService quoteService)
    {
        this.quoteService = quoteService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/random")
    public List<Quote> getRandom(@RequestParam("count") Integer count)
    {
        if (count == null || count < 1 || count > MAX_LIST_COUNT)
            count = MAX_LIST_COUNT;

        return quoteService.getRandom(count);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/author/{authorId}")
    public List<Quote> getByAuthor(@PathVariable("authorId") Long authorId)
    {
        return quoteService.getByAuthor(authorId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category/{categoryId}")
    public List<Quote> getByCategory(@PathVariable("categoryId") Long categoryId)
    {
        return quoteService.getByCategory(categoryId);
    }
}
