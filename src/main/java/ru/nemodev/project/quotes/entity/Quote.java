package ru.nemodev.project.quotes.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
@Entity
@Table(name = "QUOTE")
public class Quote implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_quote_id_seq")
    @SequenceGenerator(name = "quotes_quote_id_seq", sequenceName = "quotes_quote_id_seq", allocationSize = 1)
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "SOURCE_TYPE")
    private String sourceType;

    @Column(name = "YEAR")
    private String year;

    public Quote() { }

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

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
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
