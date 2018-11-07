package ru.nemodev.project.quotes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nemodev.project.quotes.entity.Author;
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

    @Autowired
    public AuthorEndpoint(AuthorService authorService)
    {
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Author> getList()
    {
        return authorService.getList();
    }
}
