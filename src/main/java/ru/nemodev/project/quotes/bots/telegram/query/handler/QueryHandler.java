package ru.nemodev.project.quotes.bots.telegram.query.handler;

/**
 * created by NemoDev on 14.03.2018 - 23:16
 */
public interface QueryHandler<T>
{
    T handle();
}
