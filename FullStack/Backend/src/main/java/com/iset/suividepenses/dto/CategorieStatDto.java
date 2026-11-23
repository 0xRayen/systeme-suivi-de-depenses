package com.iset.suividepenses.dto;

import java.math.BigDecimal;

public record CategorieStatDto(
        Long categorieId,
        String categorieNom,
        BigDecimal total,
        long nombreDepenses,
        double pourcentage
) {
}
