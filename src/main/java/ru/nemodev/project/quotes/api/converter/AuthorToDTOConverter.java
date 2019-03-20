package ru.nemodev.project.quotes.api.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.config.SystemProperties;
import ru.nemodev.project.quotes.entity.Author;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorToDTOConverter implements Converter<Author, AuthorDTO>
{
    private final SystemProperties systemProperties;

    public AuthorToDTOConverter(SystemProperties systemProperties)
    {
        this.systemProperties = systemProperties;
    }

    @Override
    public AuthorDTO convert(Author fromEntity)
    {
        if (fromEntity == null)
            return null;

        // TODO сохранять вычесленный путь в кеш - мапу
        String imageURL = StringUtils.isEmpty(fromEntity.getImageName())
                ? null
                : systemProperties.getHostName()
                    + systemProperties.getStaticPathBase()
                    + systemProperties.getStaticPathAuthor()
                    + fromEntity.getImageName();

        return new AuthorDTO(fromEntity.getId(), fromEntity.getFullName(), imageURL);
    }

    @Override
    public List<AuthorDTO> convertList(List<Author> fromEntityList)
    {
        if (CollectionUtils.isEmpty(fromEntityList))
            return Collections.emptyList();

       return fromEntityList.stream()
               .map(this::convert)
               .collect(Collectors.toList());
    }
}
