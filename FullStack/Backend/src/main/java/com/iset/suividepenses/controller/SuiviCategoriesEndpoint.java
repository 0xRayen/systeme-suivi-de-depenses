package com.iset.suividepenses.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iset.suividepenses.entity.Categorie;
import com.iset.suividepenses.service.CategorieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class SuiviCategoriesEndpoint {

    private final CategorieService categorieService;

    @GetMapping
    public List<Categorie> lireCategories() {
        return categorieService.findAll();
    }

    @GetMapping("/{id}")
    public Categorie lireCategorieParId(@PathVariable Long id) {
        return categorieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categorie enregistrerCategorie(@Valid @RequestBody Categorie categorie) {
        return categorieService.create(categorie);
    }

    @PutMapping("/{id}")
    public Categorie editerCategorie(@PathVariable Long id, @Valid @RequestBody Categorie categorie) {
        return categorieService.update(id, categorie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void effacerCategorie(@PathVariable Long id) {
        categorieService.delete(id);
    }
}
