package ru.nemodev.project.quotes.bots.telegram.query.info;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

/**
 * created by NemoDev on 04.04.2018 - 23:51
 */
public class MessageQueryInfo extends AbstractQueryInfo<Message>
{
    public MessageQueryInfo(QueryType queryType, MessageType messageType, Message update)
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
        return update.getChatId().toString();
    }

}
