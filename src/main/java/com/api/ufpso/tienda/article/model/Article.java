package com.api.ufpso.tienda.article.model;

import com.api.ufpso.tienda.User.model.User;
import com.api.ufpso.tienda.category.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name is required")
    @Size(max = 255, message = "name max 255 characters")
    private String name;
    @NotNull(message = "description is required")
    @Size(max = 255, message = "description max 255 characters")
    private String description;
    @NotNull(message = "stock is required")
    private Number stock;
    @NotNull(message = "price is required")
    @Size(min = 5, max = 15, message = "price max 15 characters and min 1")
    private String price;
    @NotNull(message = "dateOfAdmission is required")
    private Date dateOfAdmission;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
