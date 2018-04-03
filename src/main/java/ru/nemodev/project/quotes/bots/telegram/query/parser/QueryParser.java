package ru.nemodev.project.quotes.bots.telegram.query.parser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import ru.nemodev.project.quotes.bots.telegram.query.info.MessageType;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryInfo;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    public QueryInfo parse(Update update)
    {
        LOGGER.info("Получено запросов - {}", totalQuery.incrementAndGet());
        if (update.hasMessage())
        {
            return getFromMessage(update.getMessage());
        }

        return null;
    }

    private QueryInfo getFromMessage(Message message)
    {
        String rawText = message.getText();
        if (StringUtils.isBlank(rawText))
        {
            LOGGER.info("Не передан текст запроса, обработка запроса завершена!");
            return null;
        }

        List<String> rawParts = Arrays.asList(rawText.trim().split(TEXT_DELIMITER));

        String queryText = rawParts.get(0).
                replace(MESSAGE_COMMAND_DELIMITER, EMPTY).
                split(INLINE_MESSAGE_COMMAND_DELIMITER)[0].
                toLowerCase();
        QueryType queryType = QueryType.getByQueryText(queryText);
        if (queryType == null)
        {
            LOGGER.info(String.format(
                    "Не известный тип запроса - [%s], пользователь - [%s], обработка запроса завершена!",
                    queryText, message.getFrom()));
            return null;
        }

        List<String> commandArgs = rawParts.size() > 1
                ? rawParts.subList(1, rawParts.size())
                    .stream().map(String::toLowerCase).collect(Collectors.toList())
                : Collections.emptyList();

        QueryInfo queryInfo = new QueryInfo(
                message.getFrom(),
                message.getChatId(),
                queryType,
                MessageType.TEXT_MESSAGE,
                commandArgs);
        logSuccessParse(queryInfo);

        return queryInfo;
    }

    private void logSuccessParse(QueryInfo queryInfo)
    {
        LOGGER.info(String.format(
                "Пользователь [%s] прислал запрос с типом [%s], аргументы %s",
                queryInfo.getUser().toString(),
                queryInfo.getQueryType().name(),
                queryInfo.getArgs()
        ));
    }

}
