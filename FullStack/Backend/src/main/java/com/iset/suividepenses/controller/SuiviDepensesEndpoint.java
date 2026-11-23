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

import com.iset.suividepenses.dto.DepensePayload;
import com.iset.suividepenses.entity.Depense;
import com.iset.suividepenses.service.DepenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class SuiviDepensesEndpoint {

    private final DepenseService depenseService;

    @GetMapping
    public List<Depense> lireDepenses() {
        return depenseService.listerToutes();
    }

    @GetMapping("/{id}")
    public Depense lireDepenseParId(@PathVariable Long id) {
        return depenseService.trouverParId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Depense enregistrerDepense(@Valid @RequestBody DepensePayload payload) {
        return depenseService.creer(payload);
    }

    @PutMapping("/{id}")
    public Depense editerDepense(@PathVariable Long id, @Valid @RequestBody DepensePayload payload) {
        return depenseService.modifier(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void effacerDepense(@PathVariable Long id) {
        depenseService.supprimer(id);
    }
}
