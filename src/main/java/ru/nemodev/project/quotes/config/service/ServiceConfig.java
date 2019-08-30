package ru.nemodev.project.quotes.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.nemodev.project.quotes.repository.author.AuthorRepository;
import ru.nemodev.project.quotes.repository.category.CategoryRepository;
import ru.nemodev.project.quotes.repository.quote.QuoteOptionalRepositoryImpl;
import ru.nemodev.project.quotes.repository.quote.QuoteRepository;
import ru.nemodev.project.quotes.service.CacheApplicationListener;
import ru.nemodev.project.quotes.service.author.AuthorCacheService;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.author.AuthorServiceImpl;
import ru.nemodev.project.quotes.service.category.CategoryCacheService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.category.CategoryServiceImpl;
import ru.nemodev.project.quotes.service.quote.QuoteCacheService;
import ru.nemodev.project.quotes.service.quote.QuoteService;
import ru.nemodev.project.quotes.service.quote.QuoteServiceImpl;
import ru.nemodev.project.quotes.utils.DataBasePopulator;

import javax.persistence.EntityManager;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 19:35
 */
@Configuration
public class ServiceConfig
{
    private final EntityManager entityManager;
    private final QuoteRepository quoteRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public ServiceConfig(EntityManager entityManager, QuoteRepository quoteRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository)
    {
        this.entityManager = entityManager;
        this.quoteRepository = quoteRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public AuthorService authorServiceImpl()
    {
        return new AuthorCacheService(new AuthorServiceImpl(authorRepository));
    }

    @Bean
    public CategoryService categoryServiceImpl()
    {
        return new CategoryCacheService(new CategoryServiceImpl(categoryRepository));
    }

    @Bean
    public QuoteOptionalRepositoryImpl quoteOptionalRepository()
    {
        return new QuoteOptionalRepositoryImpl(entityManager);
    }

    @Bean
    public QuoteService quoteServiceImpl()
    {
        return new QuoteCacheService(new QuoteServiceImpl(quoteRepository));
    }

    @Bean(initMethod = "populate")
    @Profile("db-h2")
    public DataBasePopulator dataBasePopulator()
    {
        return new DataBasePopulator(quoteServiceImpl(), authorServiceImpl(), categoryServiceImpl());
    }

    @Bean
    public CacheApplicationListener cacheApplicationListener()
    {
        return new CacheApplicationListener(quoteServiceImpl(), authorServiceImpl(), categoryServiceImpl());
    }
}
