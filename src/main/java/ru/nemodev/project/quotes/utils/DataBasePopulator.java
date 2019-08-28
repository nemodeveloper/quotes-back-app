package ru.nemodev.project.quotes.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class DataBasePopulator
{
    private static final Logger log = LoggerFactory.getLogger(DataBasePopulator.class);

    private final QuoteService quoteService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public DataBasePopulator(QuoteService quoteService, AuthorService authorService, CategoryService categoryService)
    {
        this.quoteService = quoteService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    public void populate()
    {
        try
        {
            String jsonData = Files.readString(Path.of(new ClassPathResource("config/quotes.json").getURI()));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<Quote> quoteList = objectMapper.readValue(jsonData, new TypeReference<List<Quote>>(){});
            quoteList = quoteList.stream()
                    .filter(quote -> {
                        String authorName = quote.getAuthor().getFullName();
                        boolean acceptSource = quote.getSource() == null || quote.getSource().length() < 70;

                        return authorName.length() > 2
                                && authorName.length() < 50
                                && authorName.matches("(?![Ыメ])[\\s\\p{L}\\p{M}&&[^\\p{Alpha}]]+")
                                && acceptSource;
                    })
                    .filter(quote -> quote.getText().length() < 350)
                    .collect(Collectors.toList());

            Map<String, Author> existAuthorMap = new HashMap<>();
            Set<String> newAuthorList = new HashSet<>();
            Map<String, Author> authorMap = quoteList.stream()
                    .collect(Collectors.toMap(
                            quote -> quote.getAuthor().getFullName(),
                            quote -> {
                                String authorName = quote.getAuthor().getFullName();
                                if (newAuthorList.contains(authorName))
                                    return quote.getAuthor();

                                Author author = existAuthorMap.get(authorName);
                                if (author != null)
                                    return author;

                                author = authorService.findByFullName(authorName).orElse(null);
                                if (author != null)
                                {
                                    existAuthorMap.put(authorName, author);
                                    return author;
                                }

                                newAuthorList.add(authorName);
                                return quote.getAuthor();
                            }
                            , (oldValue, newValue) -> oldValue, HashMap::new));

            Map<String, Category> existCategoryMap = new HashMap<>();
            Set<String> newCategoryList = new HashSet<>();
            Map<String, Category> categoryMap = quoteList.stream()
                    .collect(Collectors.toMap(
                            quote -> quote.getCategory().getName(),
                            quote -> {
                                String categoryName = quote.getCategory().getName();
                                if (newCategoryList.contains(categoryName))
                                    return quote.getCategory();

                                Category category = existCategoryMap.get(categoryName);
                                if (category != null)
                                    return category;

                                category = categoryService.findByName(categoryName).orElse(null);
                                if (category != null)
                                {
                                    existCategoryMap.put(categoryName, category);
                                    return category;
                                }

                                newCategoryList.add(categoryName);
                                return quote.getCategory();
                            },
                            (oldValue, newValue) -> oldValue, HashMap::new));

            authorService.saveAll(authorMap.values());
            categoryService.saveAll(categoryMap.values());

            quoteList.forEach(quote -> {
                quote.setAuthor(authorMap.get(quote.getAuthor().getFullName()));
                quote.setCategory(categoryMap.get(quote.getCategory().getName()));
            });

            quoteService.saveAll(quoteList);

        }
        catch (Exception e)
        {
            log.error("Ошибка загрузки цитат из файла в БД!", e);
        }
    }
}
