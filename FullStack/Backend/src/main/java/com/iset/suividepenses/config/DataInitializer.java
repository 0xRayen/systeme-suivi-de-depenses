package com.iset.suividepenses.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iset.suividepenses.entity.Categorie;
import com.iset.suividepenses.repository.CategorieRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategorieRepository categorieRepository;

    public DataInitializer(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (categorieRepository.count() > 0) {
            return;
        }
        categorieRepository.saveAll(List.of(
                new Categorie("Alimentation", "Achats alimentaires et restauration"),
                new Categorie("Transport", "Déplacements quotidiens, bus et carburant"),
                new Categorie("Logement", "Loyer mensuel et charges d'habitation"),
                new Categorie("Loisirs", "Activités sportives, sorties et détente"),
                new Categorie("Études", "Livres, matériel scolaire et frais académiques"),
                new Categorie("Autre", "Dépenses diverses")
        ));
    }
}
