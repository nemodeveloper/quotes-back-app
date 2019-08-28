package ru.nemodev.project.quotes.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * Утилитный класс по сортировке коллекций
 */
public final class SortUtils
{
    private SortUtils() { }

    /**
     * Отсортировать коллекцию с учетом русского алфавита
     * @param collection коллекция
     * @param function функция преобразования типа в строку
     * @param <T> тип элементов в коллекции
     */
    public static <T> void sortRusString(List<T> collection, Function<T, String> function)
    {
        if (CollectionUtils.isEmpty(collection))
            return;

        Collator collator = Collator.getInstance(Locale.forLanguageTag("RU"));
        collator.setStrength(Collator.PRIMARY);

        collection.sort((o1, o2) -> collator.compare(function.apply(o1), function.apply(o2)));
    }
}
