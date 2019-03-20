package ru.nemodev.project.quotes.api.converter;

import java.util.List;

public interface Converter<FROM, TO>
{
    TO convert(FROM fromEntity);
    List<TO> convertList(List<FROM> fromEntityList);
}
