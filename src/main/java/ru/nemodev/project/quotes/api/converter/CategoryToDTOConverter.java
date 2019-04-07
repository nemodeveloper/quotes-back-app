package ru.nemodev.project.quotes.api.converter;

import org.apache.commons.lang3.StringUtils;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.config.spring.property.SystemProperty;
import ru.nemodev.project.quotes.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryToDTOConverter implements Converter<Category, CategoryDTO>
{
    private final SystemProperty systemProperty;

    public CategoryToDTOConverter(SystemProperty systemProperty)
    {
        this.systemProperty = systemProperty;
    }

    @Override
    public CategoryDTO convert(Category fromEntity)
    {
        if (fromEntity == null)
            return null;

        String imageURL = StringUtils.isEmpty(fromEntity.getImageName())
                ? null
                : systemProperty.getHostName()
                    + systemProperty.getStaticPathBase()
                    + systemProperty.getStaticPathAuthor()
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
