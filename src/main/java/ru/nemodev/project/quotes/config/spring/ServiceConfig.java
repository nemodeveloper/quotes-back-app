package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.nemodev.project.quotes.config.spring.database.HibernateConfig;
import ru.nemodev.project.quotes.repository.author.AuthorCacheRepository;
import ru.nemodev.project.quotes.repository.author.AuthorRepositoryImpl;
import ru.nemodev.project.quotes.repository.category.CategoryCacheRepository;
import ru.nemodev.project.quotes.repository.category.CategoryRepositoryImpl;
import ru.nemodev.project.quotes.repository.quote.QuoteCacheRepository;
import ru.nemodev.project.quotes.repository.quote.QuoteRepositoryImpl;
import ru.nemodev.project.quotes.service.CacheApplicationListener;
import ru.nemodev.project.quotes.service.author.AuthorServiceImpl;
import ru.nemodev.project.quotes.service.category.CategoryServiceImpl;
import ru.nemodev.project.quotes.service.quote.QuoteServiceImpl;
import ru.nemodev.project.quotes.utils.DataBasePopulator;

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
    public AuthorRepositoryImpl authorHibernateDAO()
    {
        return new AuthorRepositoryImpl(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public AuthorCacheRepository authorCacheDAO()
    {
        return new AuthorCacheRepository(authorHibernateDAO());
    }

    @Bean
    public AuthorServiceImpl authorServiceImpl()
    {
        return new AuthorServiceImpl(authorCacheDAO());
    }

    @Bean
    public CategoryRepositoryImpl categoryHibernateDAO()
    {
        return new CategoryRepositoryImpl(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public CategoryCacheRepository categoryCacheDAO()
    {
        return new CategoryCacheRepository(categoryHibernateDAO());
    }

    @Bean
    public CategoryServiceImpl categoryServiceImpl()
    {
        return new CategoryServiceImpl(categoryCacheDAO());
    }

    @Bean
    public QuoteRepositoryImpl quoteHibernateDAO()
    {
        return new QuoteRepositoryImpl(hibernateConfig.sessionFactory().getObject());
    }

    @Bean
    public QuoteCacheRepository quoteCacheDAO()
    {
        return new QuoteCacheRepository(quoteHibernateDAO());
    }

    @Bean
    public QuoteServiceImpl quoteServiceImpl()
    {
        return new QuoteServiceImpl(quoteCacheDAO());
    }

    @Bean(initMethod = "populate")
    @Profile("dev-db-embedded | dev-db-remote")
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
