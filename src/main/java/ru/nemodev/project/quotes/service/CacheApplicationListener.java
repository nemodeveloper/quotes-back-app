package ru.nemodev.project.quotes.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

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
        doHotAuthors();
        doHotCategory();
    }

    private void doHotAuthors()
    {
        List<Author> authors = authorService.getList();
        authors.forEach(author -> quoteService.getByAuthor(author.getId()));
    }

    private void doHotCategory()
    {
        List<Category> categories = categoryService.getList();
        categories.forEach(category -> quoteService.getByCategory(category.getId()));
    }
}