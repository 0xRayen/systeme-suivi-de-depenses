package com.iset.suividepenses.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iset.suividepenses.dto.RapportResumeDto;
import com.iset.suividepenses.service.RapportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rapports")
@RequiredArgsConstructor
public class SuiviRapportsEndpoint {

    private final RapportService rapportService;

    @GetMapping("/resume")
    public RapportResumeDto lireResume() {
        return rapportService.construireResume();
    }
}
