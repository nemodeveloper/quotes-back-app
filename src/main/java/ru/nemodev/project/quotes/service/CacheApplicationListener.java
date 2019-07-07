package ru.nemodev.project.quotes.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;


public class CacheApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final QuoteService quoteService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public CacheApplicationListener(QuoteService quoteService, AuthorService authorService, CategoryService categoryService)
    {
        this.quoteService = quoteService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        doHotCache();
    }

    private void doHotCache()
    {
        //loadNewQuotes();
        doHotAuthors();
        doHotCategory();
    }

    private void doHotAuthors()
    {
        authorService.getList();
    }

    private void doHotCategory()
    {
        categoryService.getList();
    }

    private void loadNewQuotes()
    {
//        try
//        {
//            // TODO прогрузить цитаты на prod и stage
//            String jsonData = Files.readString(Path.of(new ClassPathResource("new_quotes.json").getURI()));
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//            List<Quote> quoteList = objectMapper.readValue(jsonData, new TypeReference<List<Quote>>(){});
//            quoteList = quoteList.stream()
//                    .filter(quote -> {
//                        String authorName = quote.getAuthor().getFullName();
//                        return authorName.length() > 2
//                                && authorName.matches("[\\s\\p{L}\\p{M}&&[^\\p{Alpha}]]+");
//                    })
//                    .collect(Collectors.toList());
//
//            Map<String, Author> authorMap = quoteList.stream()
//                    .collect(Collectors.toMap(
//                            quote -> quote.getAuthor().getFullName(),
//                            Quote::getAuthor, (oldValue, newValue) -> oldValue, HashMap::new));
//
//            authorService.addOrUpdate(new LinkedList<>(authorMap.values()));
//
//            Map<String, Category> categoryMap = quoteList.stream()
//                    .collect(Collectors.toMap(
//                            quote -> quote.getCategory().getName(),
//                            Quote::getCategory, (oldValue, newValue) -> oldValue, HashMap::new));
//
//            categoryService.addOrUpdate(new LinkedList<>(categoryMap.values()));
//
//            quoteList.forEach(quote -> {
//                        quote.setAuthor(authorMap.get(quote.getAuthor().getFullName()));
//                        quote.setCategory(categoryMap.get(quote.getCategory().getName()));
//                    });
//
//            quoteService.addOrUpdate(quoteList);
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
    }
}