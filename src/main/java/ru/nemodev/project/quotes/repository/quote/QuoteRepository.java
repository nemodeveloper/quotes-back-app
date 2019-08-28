package ru.nemodev.project.quotes.repository.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 21:45
 */
@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>, QuoteOptionalRepository
{
    @Query("FROM Quote q JOIN FETCH q.category JOIN FETCH q.author WHERE q.author.id = :authorId")
    List<Quote> findByAuthor(@Param("authorId") Long authorId);

    @Query("FROM Quote q JOIN FETCH q.category JOIN FETCH q.author WHERE q.category.id = :categoryId")
    List<Quote> findByCategory(@Param("categoryId") Long categoryId);
}
