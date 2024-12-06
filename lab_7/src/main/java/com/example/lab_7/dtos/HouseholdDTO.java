
package com.example.lab_7.dtos;

import com.example.lab_7.entities.Pet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HouseholdDTO(
        @NotBlank(message = "Eircode must not be blank") String eircode,
        @NotNull(message = "Number of occupants cannot be null")
        @Positive(message = "Number of occupants must be positive") Integer numberOfOccupants,
        @NotNull(message = "Max number of occupants cannot be null")
        @Positive(message = "Max number of occupants must be positive") Integer maxNumberOfOccupants,
        @NotNull(message = "Owner-occupied status must be provided") Boolean ownerOccupied,
        Long id,
        List<Pet> pets
) {}