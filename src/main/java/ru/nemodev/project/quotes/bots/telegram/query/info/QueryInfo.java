package ru.nemodev.project.quotes.bots.telegram.query.info;

import org.telegram.telegrambots.api.objects.User;

import java.util.Collections;
import java.util.List;

/**
 * created by NemoDev on 13.03.2018 - 22:35
 */
public class QueryInfo
{
    private User user;
    private Long chatId;
    private QueryType queryType;
    private MessageType messageType;
    private List<String> args;

    public QueryInfo(User user, Long chatId, QueryType queryType, MessageType messageType, List<String> args)
    {
        this.user = user;
        this.chatId = chatId;
        this.queryType = queryType;
        this.messageType = messageType;
        this.args = Collections.unmodifiableList(args);
    }

    public User getUser()
    {
        return user;
    }

    public Long getChatId()
    {
        return chatId;
    }

    public QueryType getQueryType()
    {
        return queryType;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public List<String> getArgs()
    {
        return args;
    }
}
