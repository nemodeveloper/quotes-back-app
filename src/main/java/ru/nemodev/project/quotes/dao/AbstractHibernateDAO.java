package ru.nemodev.project.quotes.dao;

import org.hibernate.SessionFactory;

/**
 * created by NemoDev on 17.03.2018 - 14:18
 */
public abstract class AbstractHibernateDAO
{
    protected final SessionFactory sessionFactory;

    public AbstractHibernateDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
