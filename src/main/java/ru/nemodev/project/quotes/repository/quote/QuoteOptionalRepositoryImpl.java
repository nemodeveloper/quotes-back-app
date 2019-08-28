package ru.nemodev.project.quotes.repository.quote;

import ru.nemodev.project.quotes.entity.Quote;

import javax.persistence.EntityManager;
import java.util.List;

public class QuoteOptionalRepositoryImpl implements QuoteOptionalRepository
{
    private final EntityManager entityManager;

    public QuoteOptionalRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Quote> getRandom(Integer count)
    {
        return entityManager.createQuery("FROM Quote q JOIN FETCH q.category JOIN FETCH q.author ORDER BY RAND()", Quote.class)
                .setMaxResults(count)
                .getResultList();
    }
}