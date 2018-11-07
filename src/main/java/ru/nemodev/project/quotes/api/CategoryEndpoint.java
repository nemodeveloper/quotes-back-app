package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nemodev.project.quotes.entity.Category;
import ru.nemodev.project.quotes.service.category.CategoryService;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@Controller
@RequestMapping("/v1/category")
@ResponseBody
public class CategoryEndpoint
{
    private final CategoryService categoryService;

    @Autowired
    public CategoryEndpoint(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Category> getList()
    {
        return categoryService.getList();
    }
}
