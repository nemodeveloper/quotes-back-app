package ru.nemodev.project.quotes.dao.author;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.dao.AbstractHibernateDAO;
import ru.nemodev.project.quotes.entity.Author;

import java.util.List;

public class AuthorHibernateDAO extends AbstractHibernateDAO implements AuthorDAO
{
    public AuthorHibernateDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    public Author getById(Long authorId)
    {
        return sessionFactory.getCurrentSession().get(Author.class, authorId);
    }

    @Override
    public List<Author> getList()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Author", Author.class).list();
    }
}
