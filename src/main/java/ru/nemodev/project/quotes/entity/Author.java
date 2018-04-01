package ru.nemodev.project.quotes.entity;

import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
public class Author implements Serializable
{
    private Long id;
    private String fullName;

    public Author() {}

    public Author(Long id, String fullName)
    {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
}
