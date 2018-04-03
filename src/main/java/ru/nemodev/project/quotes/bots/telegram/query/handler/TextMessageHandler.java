package ru.nemodev.project.quotes.bots.telegram.query.handler;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryType;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 14.03.2018 - 23:28
 */
public class TextMessageHandler extends AbstractQueryHandler<SendMessage>
{
    private static final String QUOTE_NOT_FOUND = "Я ничего не нашел!";

    // TODO вынести в проперти файл
    private static final String QUOTE_BOT_INFO =
            "Привет, я бот для поиска цитат!\n" +
            "Моя база содержит около 30 тысяч различных цитат!\n" +
            "Мои команды:\n" +
            "q - получить случаную цитату;\n" +
            "a Джобс - получить цитату по автору;\n" +
            "c любовь - получить цитату по категории;";

    @Autowired
    public TextMessageHandler(QuoteService quoteService,
                                 CategoryService categoryService,
                                 AuthorService authorService)
    {
        super(quoteService, categoryService, authorService);
    }

    @Override
    public SendMessage handle()
    {
        List<Quote> quotes;
        QueryType queryType = queryInfo.getQueryType();

        // TODO лапша код, нихуя не читаемо
        if (QueryType.RANDOM == queryType)
            quotes = quoteService.getRandom(1L);
        else if (QueryType.WITH_AUTHOR == queryType)
        {
            Author author;
            if (CollectionUtils.isNotEmpty(queryInfo.getArgs()))
                author = authorService.getByName(queryInfo.getArgs().get(0));
            else
                author = authorService.getRandom(1L).get(0);

            quotes = quoteService.getRandomByAuthor(author, 1L);
        }
        else if (QueryType.WITH_CATEGORY == queryType)
        {
            Category category;
            if (CollectionUtils.isNotEmpty(queryInfo.getArgs()))
                category = categoryService.getByName(queryInfo.getArgs().get(0));
            else
                category = categoryService.getRandom(1L).get(0);

            quotes = quoteService.getRandomByCategory(category, 1L);
        }
        else if (QueryType.START == queryType)
        {
            return buildResponse(getStartInfo());
        }
        else
        {
            quotes = Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(quotes))
            return buildResponse(QUOTE_NOT_FOUND);

        return buildResponse(quotes.get(0).toString());
    }

    /**
     * @return - информация о боте
     */
    private String getStartInfo()
    {
        return QUOTE_BOT_INFO;
    }

}
