package ru.nemodev.project.quotes.config.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.nemodev.project.quotes.config.spring.database.DataBaseSource;
import ru.nemodev.project.quotes.dao.author.AuthorCacheDAO;
import ru.nemodev.project.quotes.dao.author.jdbc.AuthorJdbcDAO;
import ru.nemodev.project.quotes.dao.author.jdbc.AuthorRowMapper;
import ru.nemodev.project.quotes.dao.category.CategoryCacheDAO;
import ru.nemodev.project.quotes.dao.category.jdbc.CategoryJdbcDAO;
import ru.nemodev.project.quotes.dao.category.jdbc.CategoryRowMapper;
import ru.nemodev.project.quotes.dao.quote.QuoteCacheDAO;
import ru.nemodev.project.quotes.dao.quote.jdbc.PrepareQuoteRowMapper;
import ru.nemodev.project.quotes.dao.quote.jdbc.QuoteJdbcDAO;
import ru.nemodev.project.quotes.dao.quote.jdbc.SimpleQuoteRowMapper;
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
    @Autowired
    private DataBaseSource dataBaseSource;

    @Autowired
    private ObjectFactory<PrepareQuoteRowMapper> prepareQuoteRowMapper;

    @Bean
    public AuthorRowMapper authorRowMapper()
    {
        return new AuthorRowMapper();
    }

    @Bean
    public AuthorJdbcDAO authorJdbcDAO()
    {
        return new AuthorJdbcDAO(dataBaseSource.getNamedParameterJdbcTemplate(), authorRowMapper());
    }

    @Bean
    public AuthorCacheDAO authorCacheDAO()
    {
        return new AuthorCacheDAO(authorJdbcDAO());
    }

    @Bean
    public AuthorServiceImpl authorServiceImpl()
    {
        return new AuthorServiceImpl(authorCacheDAO());
    }

    @Bean
    public CategoryRowMapper categoryRowMapper()
    {
        return new CategoryRowMapper();
    }

    @Bean
    public CategoryJdbcDAO categoryJdbcDAO()
    {
        return new CategoryJdbcDAO(dataBaseSource.getNamedParameterJdbcTemplate(), categoryRowMapper());
    }

    @Bean
    public CategoryCacheDAO categoryCacheDAO()
    {
        return new CategoryCacheDAO(categoryJdbcDAO());
    }

    @Bean
    public CategoryServiceImpl categoryServiceImpl()
    {
        return new CategoryServiceImpl(categoryCacheDAO());
    }

    @Bean
    public SimpleQuoteRowMapper simpleQuoteRowMapper()
    {
        return new SimpleQuoteRowMapper(authorRowMapper(), categoryRowMapper());
    }

    @Bean
    @Scope("prototype")
    public PrepareQuoteRowMapper prepareQuoteRowMapper()
    {
        return new PrepareQuoteRowMapper(authorRowMapper(), categoryRowMapper());
    }

    @Bean
    public QuoteJdbcDAO quoteJdbcDAO()
    {
        return new QuoteJdbcDAO(
                dataBaseSource.getNamedParameterJdbcTemplate(),
                simpleQuoteRowMapper(),
                prepareQuoteRowMapper,
                authorServiceImpl(),
                categoryServiceImpl());
    }

    @Bean
    public QuoteCacheDAO quoteCacheDAO()
    {
        return new QuoteCacheDAO(quoteJdbcDAO());
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
