package com.iset.suividepenses.dto;

import java.math.BigDecimal;

public record MensuelStatDto(
        String mois,
        BigDecimal total,
        long nombreDepenses
) {
}
