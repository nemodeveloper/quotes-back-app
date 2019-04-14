package ru.nemodev.project.quotes.repository.quote;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.repository.AbstractJpaRepository;

import java.util.List;

public class QuoteRepositoryImpl extends AbstractJpaRepository<Quote, Long> implements QuoteRepository
{
    public QuoteRepositoryImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    protected Class<Quote> getEntityClass()
    {
        return Quote.class;
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author "+
                        "ORDER BY RAND()", getEntityClass())
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
                            "WHERE q.author.id = :authorId", getEntityClass())
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
                            "WHERE q.category.id = :categoryId", getEntityClass())
                .setParameter("categoryId", categoryId)
                .getResultList();
    }
}