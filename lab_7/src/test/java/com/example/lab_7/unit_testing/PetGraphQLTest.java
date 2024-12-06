package com.example.lab_7.unit_testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class


PetGraphQLTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllPetsByAnimalTypeWithValidType() throws Exception {
        String query = "{ getAllPetsByAnimalType(animalType: \\\"Cat\\\") { id name animalType } }";


        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\": \"{ getAllPetsByAnimalType(animalType: \\\"Cat\\\") { id name animalType } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.getAllPetsByAnimalType").isArray())
                .andExpect(jsonPath("$.data.getAllPetsByAnimalType[0].name").isString())
                .andExpect(jsonPath("$.data.getAllPetsByAnimalType[0].animalType").isString());

    }

    @Test
    public void testGetAllPetsByAnimalTypeWithNullType() throws Exception {
        String query = "{ getAllPetsByAnimalType(animalType: null) { id name animalType } }";

        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\": \"" + query + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").exists())  // Expecting an error field in the response
                .andExpect(jsonPath("$.errors[0].message").value("Validation error (NullValueForNonNullArgument@[getAllPetsByAnimalType]) : Null value for non-null field argument 'animalType'"));
    }



}
