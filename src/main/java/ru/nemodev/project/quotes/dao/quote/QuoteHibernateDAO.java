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
        return sessionFactory.getCurrentSession().createNativeQuery(
                "SELECT * FROM QUOTE q " +
                        "LEFT JOIN AUTHOR a ON q.author_id = a.id " +
                        "LEFT JOIN CATEGORY c ON q.category_id = c.id " +
                        "ORDER BY RANDOM()", Quote.class)
                .setMaxResults(count)
                .list();
    }

    @Override
    public List<Quote> getByAuthor(Long authorId)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Quote WHERE author.id = :author_id", Quote.class)
                .setParameter("author_id", authorId)
                .getResultList();
    }

    @Override
    public List<Quote> getByCategory(Long categoryId)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Quote WHERE category.id = :category_id", Quote.class)
                .setParameter("category_id", categoryId)
                .getResultList();
    }
}