package ru.nemodev.project.quotes.dao.category;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.dao.AbstractHibernateDAO;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;

public class CategoryHibernateDAO extends AbstractHibernateDAO implements CategoryDAO
{
    public CategoryHibernateDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    public Category getById(Long categoryId)
    {
        return sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public List<Category> getList()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Category", Category.class).list();
    }
}
