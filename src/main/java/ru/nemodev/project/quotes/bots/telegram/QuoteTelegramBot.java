package ru.nemodev.project.quotes.bots.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.nemodev.project.quotes.bots.telegram.query.handler.AbstractQueryHandler;
import ru.nemodev.project.quotes.bots.telegram.query.info.MessageType;
import ru.nemodev.project.quotes.bots.telegram.query.parser.QueryParser;
import ru.nemodev.project.quotes.bots.telegram.query.info.QueryInfo;
import ru.nemodev.project.quotes.bots.telegram.query.handler.QueryHandler;
import ru.nemodev.project.quotes.bots.telegram.query.handler.TextMessageHandler;

/**
 * created by NemoDev on 04.03.2018 - 19:05
 */
public class QuoteTelegramBot extends TelegramLongPollingBot
{
    private final static Logger LOGGER = LoggerFactory.getLogger(QuoteTelegramBot.class);

    @Value("${bot.quote.token}")
    private String botToken;

    @Value("${bot.quote.username}")
    private String botUsername;

    private final QueryParser queryParser;

    private final ObjectFactory<TextMessageHandler> textMessageProcessor;

    @Autowired
    public QuoteTelegramBot(
            QueryParser queryParser,
            ObjectFactory<TextMessageHandler> textMessageProcessor)
    {
        this.queryParser = queryParser;
        this.textMessageProcessor = textMessageProcessor;
    }

    @Override
    public String getBotUsername()
    {
        return this.botUsername;
    }

    @Override
    public String getBotToken()
    {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        try
        {
            BotApiMethod<?> response = getResponse(update);
            if (response == null)
                return;

            this.sendApiMethod(response);
        }
        catch (TelegramApiException e)
        {
            LOGGER.error("Не удалось отправить ответ по запросу!", e);
        }
    }

    private BotApiMethod<?> getResponse(Update update)
    {
        QueryInfo queryInfo = queryParser.parse(update);
        if (queryInfo == null)
        {
            LOGGER.warn("Бот не поддерживает переданную команду для обработки!");
            return null;
        }

        QueryHandler<? extends BotApiMethod> queryHandler = lookUpHandler(queryInfo);
        if (queryHandler == null)
        {
            LOGGER.warn("Не найден обработчик запроса с типом - {}", queryInfo.getMessageType());
            return null;
        }

        BotApiMethod<?> botApiMethod = queryHandler.handle();
        if (botApiMethod == null)
        {
            LOGGER.warn("Обработчик сообщения {} не вернул результат обработки!", queryHandler.getClass().getSimpleName());
            return null;
        }

        return botApiMethod;
    }

    private QueryHandler<? extends BotApiMethod> lookUpHandler(QueryInfo queryInfo)
    {
        AbstractQueryHandler<? extends BotApiMethod> processor = null;

        if (MessageType.TEXT_MESSAGE == queryInfo.getMessageType())
        {
            processor = textMessageProcessor.getObject();
        }

        if (processor == null)
            return null;

        processor.setQueryInfo(queryInfo);
        return processor;
    }

}
