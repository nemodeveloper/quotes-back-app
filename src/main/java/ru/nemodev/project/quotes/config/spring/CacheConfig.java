package ru.nemodev.project.quotes.config.spring;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PreDestroy;
import javax.cache.CacheManager;
import java.io.IOException;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 19:15
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfig
{
    @Bean
    public JCacheManagerFactoryBean jCacheManagerFactoryBean()
    {
        JCacheManagerFactoryBean cacheFactory = new JCacheManagerFactoryBean();
        try
        {
            cacheFactory.setCacheManagerUri(new ClassPathResource("ehcache.xml").getURI());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return cacheFactory;
    }

    @Bean
    public JCacheCacheManager jCacheCacheManager()
    {
        JCacheCacheManager manager = new JCacheCacheManager(jCacheManagerFactoryBean().getObject());
        manager.setTransactionAware(true);

        return manager;
    }

    @PreDestroy
    public void destroy()
    {
        CacheManager cacheManager = jCacheCacheManager().getCacheManager();
        cacheManager.getCacheNames().forEach(cacheManager::destroyCache);
    }
}
