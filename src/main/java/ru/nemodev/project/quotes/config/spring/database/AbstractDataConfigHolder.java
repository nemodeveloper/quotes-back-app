package ru.nemodev.project.quotes.config.spring.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ru.nemodev.project.quotes.config.spring.property.DataBaseProperty;


public abstract class AbstractDataConfigHolder implements DataSourceHolder
{
    @Autowired
    private DataBaseProperty dataBaseProperty;

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dataBaseProperty.getDriver());
        dataSource.setUrl(dataBaseProperty.getURL());
        dataSource.setUsername(dataBaseProperty.getUsername());
        dataSource.setPassword(dataBaseProperty.getPassword());
        dataSource.setInitialSize(dataBaseProperty.getSocketTimeout());
        dataSource.setMaxIdle(3);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setRemoveAbandoned(true);

        return dataSource;
    }
}
