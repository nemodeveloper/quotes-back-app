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

}
