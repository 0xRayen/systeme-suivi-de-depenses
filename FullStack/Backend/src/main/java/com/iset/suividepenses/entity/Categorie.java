package com.iset.suividepenses.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la catégorie est obligatoire.")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères.")
    @Column(nullable = false, unique = true, length = 100)
    private String nom;

    @Size(max = 255, message = "La description ne doit pas dépasser 255 caractères.")
    @Column(length = 255)
    private String description;

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
}
