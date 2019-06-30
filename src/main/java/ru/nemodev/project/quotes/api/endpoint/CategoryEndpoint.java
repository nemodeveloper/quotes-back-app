package ru.nemodev.project.quotes.api.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.api.processor.CategoryRestRequestProcessor;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@Controller
@RequestMapping("/v1/category")
@ResponseBody
public class CategoryEndpoint
{
    private final CategoryRestRequestProcessor categoryRestRequestProcessor;

    public CategoryEndpoint(CategoryRestRequestProcessor categoryRestRequestProcessor)
    {
        this.categoryRestRequestProcessor = categoryRestRequestProcessor;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<CategoryDTO> getList()
    {
        return categoryRestRequestProcessor.getList();
    }
}
