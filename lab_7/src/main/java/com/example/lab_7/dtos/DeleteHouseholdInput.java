package com.example.lab_7.dtos;

import jakarta.validation.constraints.NotNull;

public record DeleteHouseholdInput(
        @NotNull Long id,  // The ID of the household to delete
        String eircode,
        Integer numberOfOccupants,
        Integer maxNumberOfOccupants,
        Boolean ownerOccupied
) {}