package ru.nemodev.project.quotes.api.endpoint;

import org.springframework.web.bind.annotation.*;
import ru.nemodev.project.quotes.api.dto.QuoteDTO;
import ru.nemodev.project.quotes.api.processor.QuoteRestRequestProcessor;

import java.util.List;


@RestController
@RequestMapping("/v1/quote")
public class QuoteEndpoint
{
    private final QuoteRestRequestProcessor quoteRestRequestProcessor;

    public QuoteEndpoint(QuoteRestRequestProcessor quoteRestRequestProcessor)
    {
        this.quoteRestRequestProcessor = quoteRestRequestProcessor;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/random")
    public List<QuoteDTO> getRandom(@RequestParam(value = "count", defaultValue = "50") Integer count)
    {
        return quoteRestRequestProcessor.getRandom(count);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/author/{authorId}")
    public List<QuoteDTO> getByAuthor(@PathVariable("authorId") Long authorId)
    {
        return quoteRestRequestProcessor.getByAuthor(authorId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category/{categoryId}")
    public List<QuoteDTO> getByCategory(@PathVariable("categoryId") Long categoryId)
    {
        return quoteRestRequestProcessor.getByCategory(categoryId);
    }
}
