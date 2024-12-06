
package com.example.lab_7.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PetDTO(
        Long id,
        @NotBlank(message = "Pet name must not be blank") String name,
        @NotBlank(message = "Pet type must not be blank") String type,
        String breed,
        @Positive(message = "Pet age must be greater than zero") int age,
        Long householdId
) {}