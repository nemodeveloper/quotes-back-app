package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.project.quotes.config.spring.database.HibernateConfig;
import ru.nemodev.project.quotes.dao.author.AuthorCacheDAO;
import ru.nemodev.project.quotes.dao.author.AuthorHibernateDAO;
import ru.nemodev.project.quotes.dao.category.CategoryCacheDAO;
import ru.nemodev.project.quotes.dao.category.CategoryHibernateDAO;
import ru.nemodev.project.quotes.dao.quote.QuoteCacheDAO;
import ru.nemodev.project.quotes.dao.quote.QuoteHibernateDAO;
import ru.nemodev.project.quotes.service.CacheApplicationListener;
import ru.nemodev.project.quotes.service.author.AuthorServiceImpl;
import ru.nemodev.project.quotes.service.category.CategoryServiceImpl;
import ru.nemodev.project.quotes.service.quote.QuoteServiceImpl;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 19:35
 */
@Configuration
public class ServiceConfig
{
    private final HibernateConfig hibernateConfig;

    public ServiceConfig(HibernateConfig hibernateConfig)
    {
        this.hibernateConfig = hibernateConfig;
    }

    @Bean
    public AuthorHibernateDAO authorHibernateDAO()
    {
        return new AuthorHibernateDAO(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public AuthorCacheDAO authorCacheDAO()
    {
        return new AuthorCacheDAO(authorHibernateDAO());
    }

    @Bean
    public AuthorServiceImpl authorServiceImpl()
    {
        return new AuthorServiceImpl(authorCacheDAO());
    }

    @Bean
    public CategoryHibernateDAO categoryHibernateDAO()
    {
        return new CategoryHibernateDAO(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public CategoryCacheDAO categoryCacheDAO()
    {
        return new CategoryCacheDAO(categoryHibernateDAO());
    }

    @Bean
    public CategoryServiceImpl categoryServiceImpl()
    {
        return new CategoryServiceImpl(categoryCacheDAO());
    }

    @Bean
    public QuoteHibernateDAO quoteHibernateDAO()
    {
        return new QuoteHibernateDAO(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public QuoteCacheDAO quoteCacheDAO()
    {
        return new QuoteCacheDAO(quoteHibernateDAO());
    }

    @Bean
    public QuoteServiceImpl quoteServiceImpl()
    {
        return new QuoteServiceImpl(quoteCacheDAO());
    }

    @Bean
    public CacheApplicationListener cacheApplicationListener()
    {
        return new CacheApplicationListener(quoteServiceImpl(), authorServiceImpl(), categoryServiceImpl());
    }
}
