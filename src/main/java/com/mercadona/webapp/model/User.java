package com.mercadona.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pseudo", nullable = false)
    @NotEmpty(message="Le pseudo est obligatoire")
    @Size(min = 5, max = 25, message = "Le pseudo doit contenir entre 5 et 25 caracteres")
    private String pseudo;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;

    public long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
