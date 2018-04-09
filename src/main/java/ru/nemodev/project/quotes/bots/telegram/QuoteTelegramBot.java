package ru.nemodev.project.quotes.bots.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.nemodev.project.quotes.bots.telegram.query.handler.AbstractQueryHandler;
import ru.nemodev.project.quotes.bots.telegram.query.handler.CallbackQueryHandler;
import ru.nemodev.project.quotes.bots.telegram.query.handler.QueryHandler;
import ru.nemodev.project.quotes.bots.telegram.query.handler.TextMessageHandler;
import ru.nemodev.project.quotes.bots.telegram.query.info.AbstractQueryInfo;
import ru.nemodev.project.quotes.bots.telegram.query.info.MessageType;
import ru.nemodev.project.quotes.bots.telegram.query.parser.QueryParser;

/**
 * created by NemoDev on 04.03.2018 - 19:05
 */
public class QuoteTelegramBot extends TelegramLongPollingBot
{
    private final static Logger LOGGER = LoggerFactory.getLogger(QuoteTelegramBot.class);

    private final String botToken;
    private final String botUsername;

    private final QueryParser queryParser;

    private final ObjectFactory<TextMessageHandler> textMessageHandler;
    private final ObjectFactory<CallbackQueryHandler> callbackQueryHandler;

    @Autowired
    public QuoteTelegramBot(
            String botToken, String botUsername,
            QueryParser queryParser,
            ObjectFactory<TextMessageHandler> textMessageHandler,
            ObjectFactory<CallbackQueryHandler> callbackQueryHandler)
    {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.queryParser = queryParser;
        this.textMessageHandler = textMessageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
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
        AbstractQueryInfo abstractQueryInfo = queryParser.parse(update);
        if (abstractQueryInfo == null)
        {
            LOGGER.warn("Бот не поддерживает переданную команду для обработки!");
            return null;
        }

        QueryHandler<? extends BotApiMethod> queryHandler = lookUpHandler(abstractQueryInfo);
        if (queryHandler == null)
        {
            LOGGER.warn("Не найден обработчик запроса с типом - {}", abstractQueryInfo.getMessageType());
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

    private <Q extends AbstractQueryInfo<?>> QueryHandler<? extends BotApiMethod> lookUpHandler(Q queryInfo)
    {
        AbstractQueryHandler<Q, ? extends BotApiMethod> handler;

        if (MessageType.TEXT_MESSAGE == queryInfo.getMessageType())
            handler = (AbstractQueryHandler<Q, ? extends BotApiMethod>) textMessageHandler.getObject();
        else if (MessageType.CALLBACK_MESSAGE == queryInfo.getMessageType())
            handler = (AbstractQueryHandler<Q, ? extends BotApiMethod>) callbackQueryHandler.getObject();
        else
            return null;

        handler.setQueryInfo(queryInfo);
        return handler;
    }

}
