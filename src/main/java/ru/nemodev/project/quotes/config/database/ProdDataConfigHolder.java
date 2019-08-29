package ru.nemodev.project.quotes.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.nemodev.project.quotes.config.property.DataBaseProperty;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:11
 */
@Configuration
@Profile("prod")
public class ProdDataConfigHolder extends AbstractDataSourceHolder
{
    public ProdDataConfigHolder(DataBaseProperty dataBaseProperty)
    {
        super(dataBaseProperty);
    }

    @Override
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource()
    {
        return new HikariDataSource(hikariConfig());
    }
}
