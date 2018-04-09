package ru.nemodev.project.quotes.bots.telegram.query.handler;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.bots.telegram.query.info.CallbackQueryInfo;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryType;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 05.04.2018 - 0:48
 */
public class CallbackQueryHandler extends AbstractQueryHandler<CallbackQueryInfo, SendMessage>
{
    @Autowired
    public CallbackQueryHandler(QuoteService quoteService, CategoryService categoryService, AuthorService authorService)
    {
        super(quoteService, categoryService, authorService);
    }

    @Override
    public SendMessage handle()
    {
        List<Quote> quotes;
        QueryType queryType = queryInfo.getQueryType();

        if (QueryType.RANDOM == queryType)
            quotes = quoteService.getRandom(1L);
        else
            quotes = Collections.emptyList();

        if (CollectionUtils.isEmpty(quotes))
            return buildBaseSendMessage(QUOTE_NOT_FOUND);

        return buildBaseSendMessage(quotes.get(0).toString());
    }
}
