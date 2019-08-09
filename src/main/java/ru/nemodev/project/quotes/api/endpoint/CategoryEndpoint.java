package ru.nemodev.project.quotes.api.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nemodev.project.quotes.api.dto.CategoryDTO;
import ru.nemodev.project.quotes.api.processor.CategoryRestRequestProcessor;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@RestController
@RequestMapping("/v1/category")
@Api("Quote category information")
public class CategoryEndpoint
{
    private final CategoryRestRequestProcessor categoryRestRequestProcessor;

    public CategoryEndpoint(CategoryRestRequestProcessor categoryRestRequestProcessor)
    {
        this.categoryRestRequestProcessor = categoryRestRequestProcessor;
    }

    @GetMapping("/list")
    @ApiOperation(value = "View list category")
    public ResponseEntity<List<CategoryDTO>> getList()
    {
        return ResponseEntity.ok(categoryRestRequestProcessor.getList());
    }
}
