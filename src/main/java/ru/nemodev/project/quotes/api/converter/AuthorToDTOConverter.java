package ru.nemodev.project.quotes.api.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.config.property.SystemProperty;
import ru.nemodev.project.quotes.entity.Author;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorToDTOConverter implements Converter<Author, AuthorDTO>
{
    private final SystemProperty systemProperty;

    public AuthorToDTOConverter(SystemProperty systemProperty)
    {
        this.systemProperty = systemProperty;
    }

    @Override
    public AuthorDTO convert(Author fromEntity)
    {
        if (fromEntity == null)
            return null;

        String imageURL = StringUtils.isEmpty(fromEntity.getImageName())
                ? null
                : systemProperty.getHostName()
                    + systemProperty.getStaticPathBase()
                    + systemProperty.getStaticPathAuthor()
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
