package ru.nemodev.project.quotes.bots.telegram.query.handler;

import org.telegram.telegrambots.api.methods.BotApiMethod;

/**
 * created by NemoDev on 14.03.2018 - 23:16
 */
public interface QueryHandler<T extends BotApiMethod>
{
    T handle();
}
