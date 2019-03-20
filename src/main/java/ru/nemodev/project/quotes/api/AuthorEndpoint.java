package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nemodev.project.quotes.api.converter.AuthorToDTOConverter;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.service.author.AuthorService;

import java.util.List;


/**
 * created by NemoDev on 19.07.2018 - 12:21
 */
@Controller
@RequestMapping("/v1/author")
@ResponseBody
public class AuthorEndpoint
{
    private final AuthorService authorService;
    private final AuthorToDTOConverter authorToDTOConverter;

    // TODO сделать RestRequestProcessor который икапсулирует логику обработку запросов
    @Autowired
    public AuthorEndpoint(AuthorService authorService, AuthorToDTOConverter authorToDTOConverter)
    {
        this.authorService = authorService;
        this.authorToDTOConverter = authorToDTOConverter;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<AuthorDTO> getList()
    {
        return authorToDTOConverter.convertList(authorService.getList());
    }
}
