package ru.nemodev.project.quotes.api.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.project.quotes.api.dto.QuoteDTO;
import ru.nemodev.project.quotes.api.processor.QuoteRestRequestProcessor;

import java.util.List;


@RestController
@RequestMapping("/v1/quote")
@Api("Quote information")
public class QuoteEndpoint
{
    private final QuoteRestRequestProcessor quoteRestRequestProcessor;

    public QuoteEndpoint(QuoteRestRequestProcessor quoteRestRequestProcessor)
    {
        this.quoteRestRequestProcessor = quoteRestRequestProcessor;
    }

    @GetMapping("/random")
    @ApiOperation(value = "View random quote list")
    public ResponseEntity<List<QuoteDTO>> getRandom(
            @ApiParam(value = "Count random quotes, max count = 200", defaultValue = "100")
            @RequestParam(value = "count", required = false, defaultValue = "100") Integer count)
    {
        return ResponseEntity.ok(quoteRestRequestProcessor.getRandom(count));
    }

    @GetMapping("/author/{authorId}")
    @ApiOperation(value = "View quote list by author")
    public ResponseEntity<List<QuoteDTO>> getByAuthor(
            @ApiParam(value = "Id author", required = true, example = "1")
            @PathVariable("authorId") Long authorId)
    {
        return ResponseEntity.ok(quoteRestRequestProcessor.getByAuthor(authorId));
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation(value = "View quote list by category")
    public ResponseEntity<List<QuoteDTO>> getByCategory(
            @ApiParam(value = "Id category", required = true, example = "1")
            @PathVariable("categoryId") Long categoryId)
    {
        return ResponseEntity.ok(quoteRestRequestProcessor.getByCategory(categoryId));
    }
}
