package com.iset.suividepenses.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "depenses")
@Getter
@Setter
@NoArgsConstructor
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre de la dépense est obligatoire.")
    @Size(max = 150, message = "Le titre ne doit pas dépasser 150 caractères.")
    @Column(nullable = false, length = 150)
    private String titre;

    @NotNull(message = "Le montant est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le montant doit être strictement positif.")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montant;

    @NotNull(message = "La date de la dépense est obligatoire.")
    @Column(nullable = false)
    private LocalDate date;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    @Column(length = 500)
    private String description;

    @NotNull(message = "La catégorie est obligatoire.")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;
}
