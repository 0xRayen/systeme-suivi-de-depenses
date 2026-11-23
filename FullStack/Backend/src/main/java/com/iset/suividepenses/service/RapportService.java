package com.iset.suividepenses.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iset.suividepenses.dto.CategorieStatDto;
import com.iset.suividepenses.dto.MensuelStatDto;
import com.iset.suividepenses.dto.RapportResumeDto;
import com.iset.suividepenses.entity.Depense;
import com.iset.suividepenses.repository.DepenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RapportService {

    private final DepenseRepository depenseRepository;

    @Transactional(readOnly = true)
    public RapportResumeDto construireResume() {
        var depenses = depenseRepository.findAll();
        var moisCourant = YearMonth.now().toString();

        if (depenses.isEmpty()) {
            return new RapportResumeDto(
                    BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
                    0L,
                    BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
                    moisCourant,
                    List.of(),
                    List.of()
            );
        }

        var totalGlobal = BigDecimal.ZERO;
        var totalMois = BigDecimal.ZERO;
        Map<Long, CategorieAccumulateur> parCat = new LinkedHashMap<>();
        Map<String, MensuelAccumulateur> parMoisMap = new TreeMap<>();

        for (Depense d : depenses) {
            var m = d.getMontant();
            totalGlobal = totalGlobal.add(m);
            var moisCle = YearMonth.from(d.getDate()).toString();
            if (moisCle.equals(moisCourant)) {
                totalMois = totalMois.add(m);
            }

            var cat = d.getCategorie();
            parCat.computeIfAbsent(cat.getId(), k -> new CategorieAccumulateur(cat.getId(), cat.getNom()))
                    .ajouter(m);
            parMoisMap.computeIfAbsent(moisCle, MensuelAccumulateur::new)
                    .ajouter(m);
        }

        final var totalGlobalFinal = totalGlobal.setScale(2, RoundingMode.HALF_UP);
        var moyenne = totalGlobalFinal.divide(BigDecimal.valueOf(depenses.size()), 2, RoundingMode.HALF_UP);

        var statsCategories = parCat.values().stream()
                .sorted(Comparator.comparing((CategorieAccumulateur a) -> a.total).reversed())
                .map(a -> new CategorieStatDto(
                        a.id,
                        a.nom,
                        a.total.setScale(2, RoundingMode.HALF_UP),
                        a.count,
                        totalGlobalFinal.compareTo(BigDecimal.ZERO) == 0
                                ? 0.0
                                : a.total.multiply(BigDecimal.valueOf(100))
                                        .divide(totalGlobalFinal, 1, RoundingMode.HALF_UP)
                                        .doubleValue()
                ))
                .toList();

        var statsMois = parMoisMap.values().stream()
                .map(a -> new MensuelStatDto(a.mois, a.total.setScale(2, RoundingMode.HALF_UP), a.count))
                .toList();

        return new RapportResumeDto(
                totalGlobalFinal,
                totalMois.setScale(2, RoundingMode.HALF_UP),
                depenses.size(),
                moyenne,
                moisCourant,
                statsCategories,
                statsMois
        );
    }

    private static class CategorieAccumulateur {
        final Long id;
        final String nom;
        BigDecimal total = BigDecimal.ZERO;
        long count = 0;

        CategorieAccumulateur(Long id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        void ajouter(BigDecimal montant) {
            this.total = this.total.add(montant);
            this.count++;
        }
    }

    private static class MensuelAccumulateur {
        final String mois;
        BigDecimal total = BigDecimal.ZERO;
        long count = 0;

        MensuelAccumulateur(String mois) {
            this.mois = mois;
        }

        void ajouter(BigDecimal montant) {
            this.total = this.total.add(montant);
            this.count++;
        }
    }
}
