package ru.nemodev.project.quotes.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_category_id_seq")
    @SequenceGenerator(name = "quotes_category_id_seq", sequenceName = "quotes_category_id_seq", allocationSize = 1)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    public Category() { }

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

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }
}
