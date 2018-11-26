package ru.nemodev.project.quotes.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;


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
}