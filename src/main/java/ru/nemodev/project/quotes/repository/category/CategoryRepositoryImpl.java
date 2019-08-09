package ru.nemodev.project.quotes.repository.category;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.repository.AbstractHibernateRepository;

import java.util.List;

public class CategoryRepositoryImpl extends AbstractHibernateRepository<Category, Long> implements CategoryRepository
{
    public CategoryRepositoryImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, Category.class);
    }

    @Override
    public List<Category> getList()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Category ORDER BY name", entityClass).getResultList();
    }

    @Override
    public Category getByName(String name)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Category WHERE name = :name", entityClass)
                .setParameter("name", name)
                .getResultList().stream().findFirst().orElse(null);
    }
}
