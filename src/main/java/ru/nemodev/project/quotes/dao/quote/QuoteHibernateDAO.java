package ru.nemodev.project.quotes.dao.quote;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.dao.AbstractHibernateDAO;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

public class QuoteHibernateDAO extends AbstractHibernateDAO implements QuoteDAO
{
    public QuoteHibernateDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author "+
                        "ORDER BY RAND()", Quote.class)
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
                            "WHERE q.author.id = :author_id", Quote.class)
                .setParameter("author_id", authorId)
                .getResultList();
    }

    @Override
    public List<Quote> getByCategory(Long categoryId)
    {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Quote q " +
                        "JOIN FETCH q.category " +
                        "JOIN FETCH q.author " +
                            "WHERE q.category.id = :category_id", Quote.class)
                .setParameter("category_id", categoryId)
                .getResultList();
    }

    @Override
    public Quote addOrUpdate(Quote quote)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(quote);
        return quote;
    }
}