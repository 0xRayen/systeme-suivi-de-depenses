package com.iset.suividepenses;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.YearMonth;

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
class TestsRapportsApi {

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private DepenseRepository depenseRepository;

    private MockMvc client;

    @BeforeEach
    void preparer() {
        this.client = MockMvcBuilders.webAppContextSetup(this.webContext).build();
        depenseRepository.deleteAll();
    }

    @AfterEach
    void nettoyer() {
        depenseRepository.deleteAll();
    }

    @Test
    void verifierResumeBaseVide() throws Exception {
        client.perform(get("/api/rapports/resume"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalGlobal").value(0))
                .andExpect(jsonPath("$.totalMoisCourant").value(0))
                .andExpect(jsonPath("$.nombreDepenses").value(0))
                .andExpect(jsonPath("$.moyenneDepense").value(0))
                .andExpect(jsonPath("$.parCategorie", hasSize(0)))
                .andExpect(jsonPath("$.parMois", hasSize(0)));
    }

    @Test
    void verifierCalculsAgregationRapports() throws Exception {
        var alimId = categorieRepository.findByNomIgnoreCase("Alimentation").orElseThrow().getId();
        var transId = categorieRepository.findByNomIgnoreCase("Transport").orElseThrow().getId();

        var ymCourant = YearMonth.now();
        var ymPrecedent = ymCourant.minusMonths(1);
        var dateCourante1 = ymCourant.atDay(5).toString();
        var dateCourante2 = ymCourant.atDay(12).toString();
        var datePrecedente = ymPrecedent.atDay(20).toString();

        var d1 = """
                {"titre":"Courses","montant":100.00,"date":"%s","categorieId":%d}
                """.formatted(dateCourante1, alimId);
        var d2 = """
                {"titre":"Dîner","montant":50.00,"date":"%s","categorieId":%d}
                """.formatted(dateCourante2, alimId);
        var d3 = """
                {"titre":"Carburant","montant":50.00,"date":"%s","categorieId":%d}
                """.formatted(datePrecedente, transId);

        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(d1)).andExpect(status().isCreated());
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(d2)).andExpect(status().isCreated());
        client.perform(post("/api/depenses").contentType(MediaType.APPLICATION_JSON).content(d3)).andExpect(status().isCreated());

        client.perform(get("/api/rapports/resume"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalGlobal").value(200.0))
                .andExpect(jsonPath("$.totalMoisCourant").value(150.0))
                .andExpect(jsonPath("$.nombreDepenses").value(3))
                .andExpect(jsonPath("$.moyenneDepense").value(66.67))
                .andExpect(jsonPath("$.parCategorie", hasSize(2)))
                .andExpect(jsonPath("$.parCategorie[0].categorieNom").value("Alimentation"))
                .andExpect(jsonPath("$.parCategorie[0].total").value(150.0))
                .andExpect(jsonPath("$.parCategorie[0].pourcentage").value(75.0))
                .andExpect(jsonPath("$.parCategorie[1].categorieNom").value("Transport"))
                .andExpect(jsonPath("$.parCategorie[1].pourcentage").value(25.0))
                .andExpect(jsonPath("$.parMois", hasSize(2)))
                .andExpect(jsonPath("$.parMois[0].mois").value(ymPrecedent.toString()))
                .andExpect(jsonPath("$.parMois[0].total").value(50.0))
                .andExpect(jsonPath("$.parMois[1].mois").value(ymCourant.toString()))
                .andExpect(jsonPath("$.parMois[1].total").value(150.0));
    }
}
