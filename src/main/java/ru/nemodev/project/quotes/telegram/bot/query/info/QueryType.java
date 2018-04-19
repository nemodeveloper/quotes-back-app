package ru.nemodev.project.quotes.telegram.bot.query.info;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * created by NemoDev on 15.03.2018 - 22:57
 */
public enum QueryType
{
    START("start", "старт"),                    // информация о боте
    RANDOM("random", "случайная"),              // случайные цитаты
    WITH_AUTHOR("a", "автор"),                  // цитаты с автором
    WITH_CATEGORY("c", "категория");            // цитаты с категорий

    private final String queryText;
    private final String verboseQueryText;

    QueryType(String queryText, String verboseQueryText)
    {
        this.queryText = queryText;
        this.verboseQueryText = verboseQueryText;
    }

    private static final Map<String, QueryType> lookUp = initLookUp();
    private static Map<String, QueryType> initLookUp()
    {
        Map<String, QueryType> lookUp = new ConcurrentHashMap<>(
                Arrays.stream(QueryType.values()).collect(Collectors.toMap(e->e.queryText, Function.identity())));

        lookUp.putAll(new HashMap<>(
                Arrays.stream(QueryType.values()).collect(Collectors.toMap(e->e.verboseQueryText, Function.identity()))));

        return lookUp;
    }

    public static QueryType getByQueryText(String queryText)
    {
        if (StringUtils.isBlank(queryText))
            return null;

        return lookUp.get(queryText);
    }

}
