package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/v1/quote")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class QuoteEndpoint
{
    private static final Long MAX_LIST_COUNT = 200L;

    private final QuoteService quoteService;

    @Autowired
    public QuoteEndpoint(QuoteService quoteService)
    {
        this.quoteService = quoteService;
    }

    @GET
    @Path("/random")
    public Response getRandom(@QueryParam("count") Long count)
    {
        if (count == null || count < 1 || count > MAX_LIST_COUNT)
            count = MAX_LIST_COUNT;

        return Response.ok(quoteService.getRandom(count)).build();
    }

    @GET
    @Path("/author/{authorId}")
    public Response getByAuthor(@PathParam("authorId") Long authorId)
    {
        return Response.ok(quoteService.getByAuthor(authorId)).build();
    }

    @GET
    @Path("/category/{categoryId}")
    public Response getByCategory(@PathParam("categoryId") Long categoryId)
    {
        return Response.ok(quoteService.getByCategory(categoryId)).build();
    }
}
