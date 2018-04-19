package ru.nemodev.project.quotes.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
public class Quote implements Serializable
{
    private Long id;
    private Category category;
    private String text;
    private Author author;
    private String source;
    private String year;

    public Quote() { }

    public Quote(Long id, Category category, String text,
                 Author author, String source, String year)
    {
        this.id = id;
        this.category = category;
        this.text = text;
        this.author = author;
        this.source = source;
        this.year = year;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
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

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
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

    @Override
    public String toString()
    {
        StringBuilder quoteInfo = new StringBuilder(text);
        quoteInfo.append("\n").append(category.getName());

        if (author != null)
            quoteInfo.append("\n\n").append(author.getFullName());
        if (StringUtils.isNotBlank(source))
            quoteInfo.append("\n").append(source);
        if (StringUtils.isNotBlank(year))
            quoteInfo.append("\n").append(year);

        return quoteInfo.toString();
    }
}
