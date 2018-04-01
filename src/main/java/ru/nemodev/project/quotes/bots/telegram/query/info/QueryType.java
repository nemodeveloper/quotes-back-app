package ru.nemodev.project.quotes.bots.telegram.query.info;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * created by NemoDev on 15.03.2018 - 22:57
 */
public enum QueryType
{
    RANDOM("q"),                // случайные цитаты
    WITH_AUTHOR("a"),           // цитаты с автором
    WITH_CATEGORY("c");         // цитаты с категорий

    private final String queryText;

    QueryType(String queryText)
    {
        this.queryText = queryText;
    }

    private static final Map<String, QueryType> lookUp = new ConcurrentHashMap<>(
            Arrays.stream(QueryType.values()).collect(Collectors.toMap(e->e.queryText, Function.identity())));

    public static QueryType getByQueryText(String queryText)
    {
        if (StringUtils.isBlank(queryText))
            return null;

        return lookUp.get(queryText);
    }

}
