package ru.nemodev.project.quotes.repository;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * created by NemoDev on 17.03.2018 - 14:18
 */
public abstract class AbstractHibernateRepository<T, K extends Serializable> implements CrudRepository<T, K>
{
    protected final SessionFactory sessionFactory;
    protected final Class<T> entityClass;

    public AbstractHibernateRepository(SessionFactory sessionFactory, Class<T> entityClass)
    {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public T getById(K id)
    {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @Override
    public List<T> getList()
    {
        throw new NotImplementedException("Репозиторий не реализует запрос списка!");
    }

    @Override
    public T addOrUpdate(T entity)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();

        return entity;
    }

    @Override
    public List<T> addOrUpdate(List<T> entityList)
    {
        entityList.forEach(sessionFactory.getCurrentSession()::saveOrUpdate);
        sessionFactory.getCurrentSession().flush();

        return entityList;
    }
}
