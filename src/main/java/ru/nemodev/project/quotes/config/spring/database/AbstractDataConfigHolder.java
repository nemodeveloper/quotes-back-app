package ru.nemodev.project.quotes.config.spring.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ru.nemodev.project.quotes.config.spring.property.DataBaseProperty;

import java.util.concurrent.TimeUnit;


public abstract class AbstractDataConfigHolder implements DataSourceHolder
{
    @Autowired
    protected DataBaseProperty dataBaseProperty;

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource()
    {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig()
    {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(dataBaseProperty.getDriver());
        config.setJdbcUrl(dataBaseProperty.getURL());
        config.setUsername(dataBaseProperty.getUsername());
        config.setPassword(dataBaseProperty.getPassword());
        config.setConnectionTimeout(TimeUnit.SECONDS.toMillis(dataBaseProperty.getSocketTimeout()));
        config.setLeakDetectionThreshold(TimeUnit.SECONDS.toMillis(10));
        config.setMaxLifetime(TimeUnit.SECONDS.toMillis(180));
        config.setIdleTimeout(TimeUnit.SECONDS.toMillis(120));
        config.setAutoCommit(false);
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        config.setPoolName("HikariDataSourcePool");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return config;
    }
}
