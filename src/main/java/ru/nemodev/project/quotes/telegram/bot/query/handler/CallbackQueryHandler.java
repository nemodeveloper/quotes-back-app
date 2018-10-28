package ru.nemodev.project.quotes.telegram.bot.query.handler;

import org.apache.commons.collections4.CollectionUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;
import ru.nemodev.project.quotes.telegram.bot.query.info.CallbackQueryInfo;
import ru.nemodev.project.quotes.telegram.bot.query.info.QueryType;
import ru.nemodev.project.quotes.utils.QuoteUtils;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 05.04.2018 - 0:48
 */
public class CallbackQueryHandler extends AbstractQueryHandler<CallbackQueryInfo, SendMessage>
{
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

        return buildBaseSendMessage(QuoteUtils.getQuoteTextForShare(quotes.get(0)));
    }
}
