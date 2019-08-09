package ru.nemodev.project.quotes.repository.quote;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.repository.AbstractHibernateRepository;

import java.util.List;

public class QuoteRepositoryImpl extends AbstractHibernateRepository<Quote, Long> implements QuoteRepository
{
    public QuoteRepositoryImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, Quote.class);
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author "+
                        "ORDER BY RAND()", entityClass)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Quote> getByAuthor(Long authorId)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author " +
                            "WHERE q.author.id = :authorId", entityClass)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    @Override
    public List<Quote> getByCategory(Long categoryId)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author " +
                            "WHERE q.category.id = :categoryId", entityClass)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }
}