package ru.nemodev.project.quotes.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_author_id_seq")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    public Author() {}

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

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }
}
