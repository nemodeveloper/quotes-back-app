package ru.nemodev.project.quotes.api.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.api.processor.AuthorRestRequestProcessor;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@RestController
@RequestMapping("/v1/author")
public class AuthorEndpoint
{
    private final AuthorRestRequestProcessor authorRestRequestProcessor;

    public AuthorEndpoint(AuthorRestRequestProcessor authorRestRequestProcessor)
    {
        this.authorRestRequestProcessor = authorRestRequestProcessor;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<AuthorDTO> getList()
    {
        return authorRestRequestProcessor.getList();
    }
}
