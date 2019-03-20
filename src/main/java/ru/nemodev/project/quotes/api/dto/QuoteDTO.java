package ru.nemodev.project.quotes.api.dto;

import java.io.Serializable;


public class QuoteDTO implements Serializable
{
    private Long id;
    private String text;
    private String source;
    private String year;
    private CategoryDTO category;
    private AuthorDTO author;

    public QuoteDTO() { }

    public QuoteDTO(Long id, String text, String source, String year, CategoryDTO category, AuthorDTO author)
    {
        this.id = id;
        this.text = text;
        this.source = source;
        this.year = year;
        this.category = category;
        this.author = author;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public CategoryDTO getCategory()
    {
        return category;
    }

    public void setCategory(CategoryDTO category)
    {
        this.category = category;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public AuthorDTO getAuthor()
    {
        return author;
    }

    public void setAuthor(AuthorDTO author)
    {
        this.author = author;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

}
