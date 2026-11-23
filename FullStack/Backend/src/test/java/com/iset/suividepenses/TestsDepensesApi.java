package com.iset.suividepenses;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iset.suividepenses.repository.CategorieRepository;
import com.iset.suividepenses.repository.DepenseRepository;

@SpringBootTest
class TestsDepensesApi {

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private DepenseRepository depenseRepository;

    private MockMvc client;
    private Long alimentationId;

    @BeforeEach
    void preparer() {
        this.client = MockMvcBuilders.webAppContextSetup(this.webContext).build();
        depenseRepository.deleteAll();
        this.alimentationId = categorieRepository.findByNomIgnoreCase("Alimentation")
                .orElseThrow()
                .getId();
    }

    @AfterEach
    void nettoyer() {
        depenseRepository.deleteAll();
    }

    @Test
    void verifierAjoutEtTriDepenses() throws Exception {
        var ancienne = """
                {"titre":"Pain","montant":3.50,"date":"2026-11-10","categorieId":%d,"description":"Matin"}
                """.formatted(alimentationId);
        var recente = """
                {"titre":"Déjeuner","montant":14.20,"date":"2026-11-22","categorieId":%d,"description":"Resto"}
                """.formatted(alimentationId);

        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(ancienne))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categorie.nom").value("Alimentation"));

        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(recente))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titre").value("Déjeuner"));

        client.perform(get("/api/depenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].titre").value("Déjeuner"))
                .andExpect(jsonPath("$[1].titre").value("Pain"));
    }

    @Test
    void verifierValidationsDepense() throws Exception {
        var montantInvalide = """
                {"titre":"Achat","montant":0,"date":"2026-11-22","categorieId":%d}
                """.formatted(alimentationId);
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(montantInvalide))
                .andExpect(status().isBadRequest());

        var titreVide = """
                {"titre":"  ","montant":10.00,"date":"2026-11-22","categorieId":%d}
                """.formatted(alimentationId);
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(titreVide))
                .andExpect(status().isBadRequest());

        var catInconnue = """
                {"titre":"Taxi","montant":12.00,"date":"2026-11-22","categorieId":999999}
                """;
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(catInconnue))
                .andExpect(status().isNotFound());
    }

    @Test
    void verifierBlocageSuppressionCategorieLiee() throws Exception {
        var catJson = """
                {"nom":"Temp Liée","description":"Test lien"}
                """;
        var catResp = client.perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON).content(catJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        var catId = Long.parseLong(catResp.replaceAll(".*\"id\"\\s*:\\s*(\\d+).*", "$1"));

        var depJson = """
                {"titre":"Billet","montant":25.00,"date":"2026-11-22","categorieId":%d}
                """.formatted(catId);
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(depJson))
                .andExpect(status().isCreated());

        client.perform(delete("/api/categories/" + catId))
                .andExpect(status().isConflict());

        depenseRepository.deleteAll();
        client.perform(delete("/api/categories/" + catId))
                .andExpect(status().isNoContent());
    }

    @Test
    void verifierModificationSuppressionEt404Depense() throws Exception {
        var creeJson = """
                {"titre":"Ticket Bus","montant":2.00,"date":"2026-11-21","categorieId":%d,"description":"Trajet"}
                """.formatted(alimentationId);
        var resp = client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(creeJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        var id = Long.parseLong(resp.replaceAll(".*\"id\"\\s*:\\s*(\\d+).*", "$1"));

        client.perform(get("/api/depenses/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Ticket Bus"));

        var majJson = """
                {"titre":"Taxi","montant":9.50,"date":"2026-11-22","categorieId":%d,"description":"Urgence"}
                """.formatted(alimentationId);
        client.perform(put("/api/depenses/" + id).contentType(MediaType.APPLICATION_JSON).content(majJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Taxi"))
                .andExpect(jsonPath("$.montant").value(9.50));

        client.perform(delete("/api/depenses/" + id))
                .andExpect(status().isNoContent());

        client.perform(get("/api/depenses/" + id))
                .andExpect(status().isNotFound());
        client.perform(put("/api/depenses/999999").contentType(MediaType.APPLICATION_JSON).content(majJson))
                .andExpect(status().isNotFound());
        client.perform(delete("/api/depenses/999999"))
                .andExpect(status().isNotFound());
    }
}
