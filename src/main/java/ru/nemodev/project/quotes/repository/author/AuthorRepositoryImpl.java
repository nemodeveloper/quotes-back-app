package ru.nemodev.project.quotes.repository.author;

import org.hibernate.SessionFactory;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.repository.AbstractJpaRepository;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

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
        List<Author> result = sessionFactory.getCurrentSession().createQuery("FROM Author", Author.class).getResultList();

        // в БД не корректная сортировка буквы ё
        Collator collator = Collator.getInstance(Locale.forLanguageTag("RU"));
        collator.setStrength(Collator.PRIMARY);
        result.sort((o1, o2) -> collator.compare(o1.getFullName(), o2.getFullName()));

        return result;
    }

    @Override
    public Author getByFullName(String fullName)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Author WHERE fullName = :fullName", getEntityClass())
                .setParameter("fullName", fullName)
                .getResultList().stream().findFirst().orElse(null);
    }
}
