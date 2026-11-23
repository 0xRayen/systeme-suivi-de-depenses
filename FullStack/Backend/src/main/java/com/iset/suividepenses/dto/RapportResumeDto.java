package com.iset.suividepenses.dto;

import java.math.BigDecimal;
import java.util.List;

public record RapportResumeDto(
        BigDecimal totalGlobal,
        BigDecimal totalMoisCourant,
        long nombreDepenses,
        BigDecimal moyenneDepense,
        String moisCourant,
        List<CategorieStatDto> parCategorie,
        List<MensuelStatDto> parMois
) {
}
