package ru.nemodev.project.quotes.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.project.quotes.entity.Category;

import java.util.Optional;


/**
 * created by NemoDev on 13.03.2018 - 21:46
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Optional<Category> findByName(String name);
}
