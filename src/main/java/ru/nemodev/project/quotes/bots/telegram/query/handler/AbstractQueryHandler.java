package ru.nemodev.project.quotes.bots.telegram.query.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.quote.QuoteService;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryInfo;
import ru.nemodev.project.quotes.service.category.CategoryService;

/**
 * created by NemoDev on 14.03.2018 - 23:25
 */
public abstract class AbstractQueryHandler<T extends BotApiMethod> implements QueryHandler<T>
{
    protected final QuoteService quoteService;
    protected final CategoryService categoryService;
    protected final AuthorService authorService;

    protected QueryInfo queryInfo;

    @Autowired
    protected AbstractQueryHandler(QuoteService quoteService,
                                   CategoryService categoryService,
                                   AuthorService authorService)
    {
        this.quoteService = quoteService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public void setQueryInfo(QueryInfo queryInfo)
    {
        this.queryInfo = queryInfo;
    }

    protected SendMessage buildResponse(String text)
    {
        return new SendMessage(queryInfo.getChatId(), text);
    }

}
