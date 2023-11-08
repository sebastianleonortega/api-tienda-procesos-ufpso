package com.api.ufpso.tienda.User.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Firs name is required")
    @Size(max = 255, message = "First name max 255 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(max = 255, message = "Last name max 255 characters")
    private String lastName;

    @NotNull(message = "Document is  required")
    @Size(min = 5, max = 15, message = "document max 15 characters and min 5")
    private String document;

    @Size(max = 15, min = 10, message = "Phone max 15 characters and min 10")
    private String phone;

    @NotNull(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 8, message = "Password min 8 characters")
    private String password;
}
