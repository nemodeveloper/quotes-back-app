package ru.nemodev.project.quotes.api.processor;

import ru.nemodev.project.quotes.api.converter.CategoryToDTOConverter;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.service.category.CategoryService;

import java.util.List;

public class CategoryRestRequestProcessor
{
    private final CategoryService categoryService;
    private final CategoryToDTOConverter categoryToDTOConverter;

    public CategoryRestRequestProcessor(CategoryService categoryService, CategoryToDTOConverter categoryToDTOConverter)
    {
        this.categoryService = categoryService;
        this.categoryToDTOConverter = categoryToDTOConverter;
    }

    public List<CategoryDTO> getList()
    {
        return categoryToDTOConverter.convertList(categoryService.findAll());
    }
}
