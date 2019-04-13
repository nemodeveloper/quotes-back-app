package ru.nemodev.project.quotes.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class CacheApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final QuoteService quoteService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public CacheApplicationListener(QuoteService quoteService, AuthorService authorService, CategoryService categoryService)
    {
        this.quoteService = quoteService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        doHotCache();
    }

    private void doHotCache()
    {
        loadNewQuotes();
        doHotAuthors();
        doHotCategory();
    }

    private void doHotAuthors()
    {
        authorService.getList();
    }

    private void doHotCategory()
    {
        categoryService.getList();
    }

    private void loadNewQuotes()
    {
        try
        {
            String jsonData = Files.readString(Path.of(new ClassPathResource("new_quotes.json").getURI()));
            ObjectMapper objectMapper = new ObjectMapper();
            List<Quote> quoteList = objectMapper.readValue(jsonData, new TypeReference<List<Quote>>(){});

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}