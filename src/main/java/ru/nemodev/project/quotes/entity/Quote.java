package ru.nemodev.project.quotes.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
@Getter @Setter
@Entity
@Table(name = "QUOTE")
public class Quote implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_quote_id_seq")
    @SequenceGenerator(name = "quotes_quote_id_seq", sequenceName = "quotes_quote_id_seq", allocationSize = 1000)
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

    @Column(name = "YEAR")
    private String year;

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
