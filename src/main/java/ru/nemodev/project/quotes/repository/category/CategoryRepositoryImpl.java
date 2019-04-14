package ru.nemodev.project.quotes.repository.category;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.repository.AbstractJpaRepository;

import java.util.List;

public class CategoryRepositoryImpl extends AbstractJpaRepository<Category, Long> implements CategoryRepository
{
    public CategoryRepositoryImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    protected Class<Category> getEntityClass()
    {
        return Category.class;
    }

    @Override
    public List<Category> getList()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Category ORDER BY name", Category.class).getResultList();
    }

    @Override
    public Category getByName(String name)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Category WHERE name = :name", getEntityClass())
                .setParameter("name", name)
                .getResultList().stream().findFirst().orElse(null);
    }
}
