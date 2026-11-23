package com.iset.suividepenses.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iset.suividepenses.entity.Categorie;
import com.iset.suividepenses.exception.BadRequestException;
import com.iset.suividepenses.exception.ConflictException;
import com.iset.suividepenses.exception.ResourceNotFoundException;
import com.iset.suividepenses.repository.CategorieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorieService {

    private final CategorieRepository repository;

    @Transactional(readOnly = true)
    public List<Categorie> findAll() {
        return repository.findAllByOrderByNomAsc();
    }

    @Transactional(readOnly = true)
    public Categorie findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aucune catégorie trouvée avec l'id " + id));
    }

    @Transactional
    public Categorie create(Categorie requete) {
        String nomNettoye = exigerNom(requete.getNom());
        if (repository.existsByNomIgnoreCase(nomNettoye)) {
            throw new ConflictException("La catégorie « " + nomNettoye + " » existe déjà.");
        }
        String desc = nettoyerOptionnel(requete.getDescription());
        return repository.save(new Categorie(nomNettoye, desc));
    }

    @Transactional
    public Categorie update(Long id, Categorie requete) {
        Categorie cible = findById(id);
        String nomNettoye = exigerNom(requete.getNom());
        if (repository.existsByNomIgnoreCaseAndIdNot(nomNettoye, id)) {
            throw new ConflictException("Une catégorie porte déjà le nom « " + nomNettoye + " ».");
        }
        cible.setNom(nomNettoye);
        cible.setDescription(nettoyerOptionnel(requete.getDescription()));
        return repository.save(cible);
    }

    @Transactional
    public void delete(Long id) {
        Categorie cible = findById(id);
        repository.delete(cible);
    }

    private String exigerNom(String valeur) {
        String nettoye = valeur == null ? "" : valeur.trim();
        if (nettoye.isEmpty()) {
            throw new BadRequestException("Le nom de la catégorie est obligatoire.");
        }
        return nettoye;
    }

    private String nettoyerOptionnel(String valeur) {
        if (valeur == null) {
            return null;
        }
        String nettoye = valeur.trim();
        return nettoye.isEmpty() ? null : nettoye;
    }
}
