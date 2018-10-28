package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.service.category.CategoryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * created by NemoDev on 19.07.2018 - 12:21
 */

@Path("/v1/category")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CategoryEndpoint
{
    private final CategoryService categoryService;

    @Autowired
    public CategoryEndpoint(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GET
    @Path("/list")
    public Response getList()
    {
        return Response.ok(categoryService.getList()).build();
    }
}
