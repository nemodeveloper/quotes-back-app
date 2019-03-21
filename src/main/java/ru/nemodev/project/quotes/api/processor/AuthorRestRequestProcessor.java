package ru.nemodev.project.quotes.api.processor;

import ru.nemodev.project.quotes.api.converter.AuthorToDTOConverter;
import ru.nemodev.project.quotes.api.dto.AuthorDTO;
import ru.nemodev.project.quotes.service.author.AuthorService;

import java.util.List;

public class AuthorRestRequestProcessor
{
    private final AuthorService authorService;
    private final AuthorToDTOConverter authorToDTOConverter;

    public AuthorRestRequestProcessor(AuthorService authorService, AuthorToDTOConverter authorToDTOConverter)
    {
        this.authorService = authorService;
        this.authorToDTOConverter = authorToDTOConverter;
    }

    public List<AuthorDTO> getList()
    {
        return authorToDTOConverter.convertList(authorService.getList());
    }
}
