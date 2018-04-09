package ru.nemodev.project.quotes.bots.telegram.query.info;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.User;

/**
 * created by NemoDev on 13.03.2018 - 22:35
 */
public abstract class AbstractQueryInfo<T extends BotApiObject>
{
    protected QueryType queryType;
    protected MessageType messageType;
    protected T update;

    protected AbstractQueryInfo(QueryType queryType, MessageType messageType, T update)
    {
        this.queryType = queryType;
        this.messageType = messageType;
        this.update = update;
    }

    public abstract User getUser();

    public abstract String getChatId();

    public T getUpdate()
    {
        return update;
    }

    public QueryType getQueryType()
    {
        return queryType;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

}
