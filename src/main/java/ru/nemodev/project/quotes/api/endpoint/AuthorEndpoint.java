package ru.nemodev.project.quotes.api.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.api.processor.AuthorRestRequestProcessor;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@RestController
@RequestMapping("/v1/author")
@Api("Quote author information")
public class AuthorEndpoint
{
    private final AuthorRestRequestProcessor authorRestRequestProcessor;

    public AuthorEndpoint(AuthorRestRequestProcessor authorRestRequestProcessor)
    {
        this.authorRestRequestProcessor = authorRestRequestProcessor;
    }

    @GetMapping("/list")
    @ApiOperation(value = "View list author")
    public ResponseEntity<List<AuthorDTO>> getList()
    {
        return ResponseEntity.ok(authorRestRequestProcessor.getList());
    }
}
