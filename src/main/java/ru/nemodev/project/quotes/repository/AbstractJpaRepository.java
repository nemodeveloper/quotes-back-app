package ru.nemodev.project.quotes.repository;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * created by NemoDev on 17.03.2018 - 14:18
 */
public abstract class AbstractJpaRepository<T, K extends Serializable> implements CrudRepository<T, K>
{
    protected final SessionFactory sessionFactory;

    public AbstractJpaRepository(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T getById(K id)
    {
        return sessionFactory.getCurrentSession().get(getEntityClass(), id);
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
