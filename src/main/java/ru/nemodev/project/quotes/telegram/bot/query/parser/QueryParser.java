package ru.nemodev.project.quotes.telegram.bot.query.parser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import ru.nemodev.project.quotes.telegram.bot.query.info.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * created by NemoDev on 13.03.2018 - 22:14
 */
public class QueryParser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryParser.class);

    private static final String TEXT_DELIMITER = " ";
    private static final String MESSAGE_COMMAND_DELIMITER = "/";
    private static final String INLINE_MESSAGE_COMMAND_DELIMITER = "@";
    private static final String EMPTY = "";

    private static final AtomicLong totalQuery = new AtomicLong(0);

    public AbstractQueryInfo parse(Update update)
    {
        LOGGER.info("Получено запросов - {}", totalQuery.incrementAndGet());

        if (update.hasMessage())
            return getFromMessage(update.getMessage());

        if (update.hasCallbackQuery())
            return getFromCallbackQuery(update.getCallbackQuery());

        return null;
    }

    private QueryType getQueryType(String rawQueryText)
    {
        if (StringUtils.isBlank(rawQueryText))
        {
            LOGGER.info("Не передан текст запроса, обработка запроса завершена!");
            return null;
        }

        List<String> rawParts = Arrays.asList(rawQueryText.trim().split(TEXT_DELIMITER));

        String queryText = rawParts.get(0).
                replace(MESSAGE_COMMAND_DELIMITER, EMPTY).
                split(INLINE_MESSAGE_COMMAND_DELIMITER)[0].
                toLowerCase();

        return QueryType.getByQueryText(queryText);
    }

    private AbstractQueryInfo getFromMessage(Message message)
    {
        final String queryText = message.getText();
        final QueryType queryType = getQueryType(queryText);

        if (queryType == null)
        {
            logUnparseQueryText(queryText, message.getFrom());
            return null;
        }

        MessageQueryInfo queryInfo = new MessageQueryInfo(queryType, MessageType.TEXT_MESSAGE, message);
        logSuccessParse(queryInfo);

        return queryInfo;
    }

    private CallbackQueryInfo getFromCallbackQuery(CallbackQuery callbackQuery)
    {
        final String queryText = callbackQuery.getData();
        final QueryType queryType = getQueryType(queryText);

        if (queryType == null)
        {
            logUnparseQueryText(queryText, callbackQuery.getFrom());
            return null;
        }

        CallbackQueryInfo queryInfo = new CallbackQueryInfo(queryType, MessageType.CALLBACK_MESSAGE, callbackQuery);
        logSuccessParse(queryInfo);

        return queryInfo;
    }

    private void logUnparseQueryText(String queryText, User user)
    {
        LOGGER.warn(String.format(
                "Не известный тип запроса - [%s], пользователь - [%s], обработка запроса завершена!",
                queryText, user));
    }


    private void logSuccessParse(AbstractQueryInfo<? extends BotApiObject> queryInfo)
    {
        LOGGER.info(String.format(
                "Пользователь [%s] прислал запрос с типом [%s], запрос %s",
                queryInfo.getUser(),
                queryInfo.getMessageType().name(),
                queryInfo.getQueryType().name()
        ));
    }

}
