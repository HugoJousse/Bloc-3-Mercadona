package com.mercadona.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nameCategory")
    @NotEmpty(message="Le nom de la catégorie est obligatoire")
    @Size(min = 3, max = 25, message = "Le nom de la catégorie doit contenir entre 3 et 25 caracteres")
    private String nameCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
