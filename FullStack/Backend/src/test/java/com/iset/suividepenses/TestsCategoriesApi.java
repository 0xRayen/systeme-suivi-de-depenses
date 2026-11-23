package com.iset.suividepenses;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class TestsCategoriesApi {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc client;

    @BeforeEach
    void initClient() {
        this.client = MockMvcBuilders.webAppContextSetup(this.webContext).build();
    }

    @Test
    void verifierCategoriesParDefaut() throws Exception {
        client.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[*].nom", hasItem("Alimentation")))
                .andExpect(jsonPath("$[*].nom", hasItem("Transport")))
                .andExpect(jsonPath("$[*].nom", hasItem("Logement")))
                .andExpect(jsonPath("$[*].nom", hasItem("Loisirs")))
                .andExpect(jsonPath("$[*].nom", hasItem("Études")))
                .andExpect(jsonPath("$[*].nom", hasItem("Autre")));
    }

    @Test
    void verifierCycleCompletCrudCategorie() throws Exception {
        var payload = """
                {"nom": "Santé Temporaire", "description": "Pharmacie"}
                """;
        var reponse = client.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Santé Temporaire"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var id = reponse.replaceAll(".*\"id\"\\s*:\\s*(\\d+).*", "$1");

        client.perform(get("/api/categories/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Santé Temporaire"));

        var maj = """
                {"nom": "Santé Modifiée", "description": "Médecin et soins"}
                """;
        client.perform(put("/api/categories/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(maj))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Santé Modifiée"));

        client.perform(delete("/api/categories/" + id))
                .andExpect(status().isNoContent());

        client.perform(get("/api/categories/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void verifierRejetsValidationEtDoublon() throws Exception {
        var doublon = """
                {"nom": "alimentation", "description": "Doublon"}
                """;
        client.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doublon))
                .andExpect(status().isConflict());

        var vide = """
                {"nom": "   ", "description": "Vide"}
                """;
        client.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vide))
                .andExpect(status().isBadRequest());

        client.perform(get("/api/categories/999999"))
                .andExpect(status().isNotFound());
    }
}
