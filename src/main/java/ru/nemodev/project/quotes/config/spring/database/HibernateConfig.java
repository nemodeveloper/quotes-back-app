package ru.nemodev.project.quotes.config.spring.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.nemodev.project.quotes.config.spring.property.PropertyConfig;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConfig
{
    private static final String ENTITY_PACKAGE = "ru.nemodev.project.quotes.entity";

    private final PropertyConfig propertyConfig;
    private final DataSourceHolder dataSourceHolder;

    public HibernateConfig(PropertyConfig propertyConfig, DataSourceHolder dataSourceHolder)
    {
        this.propertyConfig = propertyConfig;
        this.dataSourceHolder = dataSourceHolder;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceHolder.dataSource());
        sessionFactory.setPackagesToScan(ENTITY_PACKAGE);
        sessionFactory.setHibernateProperties(propertyConfig.hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager()
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
