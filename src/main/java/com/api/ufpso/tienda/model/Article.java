package com.api.ufpso.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Number stock;
    private String price;
    private Date dateOfAdmission;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
}
