package ru.nemodev.project.quotes.api.converter;

import org.apache.commons.lang3.StringUtils;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.config.SystemProperties;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryToDTOConverter implements Converter<Category, CategoryDTO>
{
    private final SystemProperties systemProperties;

    public CategoryToDTOConverter(SystemProperties systemProperties)
    {
        this.systemProperties = systemProperties;
    }

    @Override
    public CategoryDTO convert(Category fromEntity)
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

        return new CategoryDTO(fromEntity.getId(), fromEntity.getName(), imageURL);
    }

    @Override
    public List<CategoryDTO> convertList(List<Category> fromEntityList)
    {
        return fromEntityList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
