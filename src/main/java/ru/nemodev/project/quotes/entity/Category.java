package ru.nemodev.project.quotes.entity;

import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
public class Category implements Serializable
{
    private Long id;
    private String name;

    public Category() { }

    public Category(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
