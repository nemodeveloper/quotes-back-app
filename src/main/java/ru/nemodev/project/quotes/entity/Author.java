package ru.nemodev.project.quotes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by NemoDev on 13.03.2018 - 21:49
 */
@Getter @Setter
@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_author_id_seq")
    @SequenceGenerator(name = "quotes_author_id_seq", sequenceName = "quotes_author_id_seq", allocationSize = 100)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "IMAGE_NAME")
    private String imageName;

}
