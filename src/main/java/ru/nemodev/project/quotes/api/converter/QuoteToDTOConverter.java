package ru.nemodev.project.quotes.api.converter;

import org.apache.commons.collections4.CollectionUtils;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.api.dto.QuoteDTO;
import ru.nemodev.project.quotes.entity.Author;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.entity.Quote;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteToDTOConverter implements Converter<Quote, QuoteDTO>
{
    private final Converter<Author, AuthorDTO> authorDTOConverter;
    private final Converter<Category, CategoryDTO> categoryDTOConverter;

    public QuoteToDTOConverter(Converter<Author, AuthorDTO> authorDTOConverter,
                               Converter<Category, CategoryDTO> categoryDTOConverter)
    {
        this.authorDTOConverter = authorDTOConverter;
        this.categoryDTOConverter = categoryDTOConverter;
    }

    @Override
    public QuoteDTO convert(Quote quote)
    {
        if (quote == null)
            return null;

        return new QuoteDTO(quote.getId(), quote.getText(), quote.getSource(), quote.getYear(),
                categoryDTOConverter.convert(quote.getCategory()),
                authorDTOConverter.convert(quote.getAuthor()));
    }

    @Override
    public List<QuoteDTO> convertList(List<Quote> quoteList)
    {
        if (CollectionUtils.isEmpty(quoteList))
            return Collections.emptyList();

        return quoteList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
