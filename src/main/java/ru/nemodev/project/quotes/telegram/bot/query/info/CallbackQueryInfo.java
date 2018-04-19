package ru.nemodev.project.quotes.telegram.bot.query.info;

import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.User;

/**
 * created by NemoDev on 05.04.2018 - 1:01
 */
public class CallbackQueryInfo extends AbstractQueryInfo<CallbackQuery>
{

    public CallbackQueryInfo(QueryType queryType, MessageType messageType, CallbackQuery update)
    {
        super(queryType, messageType, update);
    }

    @Override
    public User getUser()
    {
        return update.getFrom();
    }

    @Override
    public String getChatId()
    {
        return update.getMessage().getChatId().toString();
    }
}
