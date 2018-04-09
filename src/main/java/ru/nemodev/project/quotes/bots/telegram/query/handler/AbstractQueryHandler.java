package ru.nemodev.project.quotes.bots.telegram.query.handler;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.bots.telegram.query.info.AbstractQueryInfo;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

/**
 * created by NemoDev on 14.03.2018 - 23:25
 */
public abstract class AbstractQueryHandler<Q extends AbstractQueryInfo<?>, T extends BotApiMethod> implements QueryHandler<T>
{
    protected static final String QUOTE_NOT_FOUND = "Я ничего не нашел!";

    protected final QuoteService quoteService;
    protected final CategoryService categoryService;
    protected final AuthorService authorService;

    protected Q queryInfo;

    protected AbstractQueryHandler(QuoteService quoteService,
                                   CategoryService categoryService,
                                   AuthorService authorService)
    {
        this.quoteService = quoteService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public void setQueryInfo(Q queryInfo)
    {
        this.queryInfo = queryInfo;
    }

    protected SendMessage buildBaseSendMessage(String text)
    {
        SendMessage baseSendMessage = new SendMessage();
        baseSendMessage.setChatId(queryInfo.getChatId());
        baseSendMessage.setText(text);

        return baseSendMessage;
    }

}
