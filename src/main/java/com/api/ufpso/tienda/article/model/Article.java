package com.api.ufpso.tienda.article.model;

import com.api.ufpso.tienda.category.model.Category;
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
