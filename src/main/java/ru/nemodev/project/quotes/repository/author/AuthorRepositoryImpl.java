package ru.nemodev.project.quotes.repository.author;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.repository.AbstractJpaRepository;

import java.util.List;

public class AuthorRepositoryImpl extends AbstractJpaRepository<Author, Long> implements AuthorRepository
{
    public AuthorRepositoryImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    @Override
    protected Class<Author> getEntityClass()
    {
        return Author.class;
    }

    @Override
    public List<Author> getList()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Author ORDER BY fullName", Author.class).getResultList();
    }

    @Override
    public Author getByFullName(String fullName)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Author WHERE fullName = :fullName", getEntityClass())
                .setParameter("fullName", fullName)
                .getResultList().stream().findFirst().orElse(null);
    }
}
