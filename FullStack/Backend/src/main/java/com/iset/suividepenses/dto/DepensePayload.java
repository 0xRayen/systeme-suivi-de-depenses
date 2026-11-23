package com.iset.suividepenses.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepensePayload(
        @NotBlank(message = "Le titre de la dépense est obligatoire.")
        @Size(max = 150, message = "Le titre ne doit pas dépasser 150 caractères.")
        String titre,

        @NotNull(message = "Le montant est obligatoire.")
        @DecimalMin(value = "0.01", message = "Le montant doit être strictement positif.")
        BigDecimal montant,

        @NotNull(message = "La date de la dépense est obligatoire.")
        LocalDate date,

        @NotNull(message = "La catégorie est obligatoire.")
        Long categorieId,

        @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
        String description
) {
}
