package ru.nemodev.project.quotes.rest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nemodev.project.quotes.service.quote.QuoteService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/test")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class Test
{
    private final QuoteService quoteService;

    @Autowired
    public Test(QuoteService quoteService)
    {
        this.quoteService = quoteService;
    }

    @GET
    public Response get()
    {
        return Response.ok(quoteService.getRandom(1L)).build();
    }

}
