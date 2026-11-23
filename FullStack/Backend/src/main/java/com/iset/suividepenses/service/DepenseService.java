package com.iset.suividepenses.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iset.suividepenses.dto.DepensePayload;
import com.iset.suividepenses.entity.Depense;
import com.iset.suividepenses.exception.BadRequestException;
import com.iset.suividepenses.exception.ResourceNotFoundException;
import com.iset.suividepenses.repository.CategorieRepository;
import com.iset.suividepenses.repository.DepenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepenseService {

    private final DepenseRepository depenseRepository;
    private final CategorieRepository categorieRepository;

    @Transactional(readOnly = true)
    public List<Depense> listerToutes() {
        return depenseRepository.findAllByOrderByDateDescIdDesc();
    }

    @Transactional
    public Depense creer(DepensePayload payload) {
        var depense = new Depense();
        appliquerChamps(depense, payload);
        return depenseRepository.save(depense);
    }

    protected void appliquerChamps(Depense cible, DepensePayload payload) {
        var titreNettoye = payload.titre() == null ? "" : payload.titre().trim();
        if (titreNettoye.isEmpty()) {
            throw new BadRequestException("Le titre de la dépense est obligatoire.");
        }
        if (payload.montant() == null || payload.montant().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Le montant doit être strictement supérieur à zéro.");
        }
        var categorie = categorieRepository.findById(payload.categorieId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Catégorie introuvable pour l'identifiant " + payload.categorieId()));

        cible.setTitre(titreNettoye);
        cible.setMontant(payload.montant());
        cible.setDate(payload.date());
        cible.setCategorie(categorie);
        var desc = payload.description() == null ? null : payload.description().trim();
        cible.setDescription(desc == null || desc.isEmpty() ? null : desc);
    }
}
