package com.api.ufpso.tienda.category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "nameCategory name is required")
    @Size(max = 255, message = "nameCategory name max 255 characters")
    private String nameCategory;

}
