package ru.nemodev.project.quotes.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements Serializable
{
    private Long id;
    private String fullName;
    private String imageURL;
}
