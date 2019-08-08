package ru.nemodev.project.quotes.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO implements Serializable
{
    private Long id;
    private String text;
    private String source;
    private String year;
    private CategoryDTO category;
    private AuthorDTO author;
}
