/*

package com.example.lab_7.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record NewHousehold(
        @NotEmpty(message = "Eircode must not be blank") String eircode,
        @Min(value = 1, message = "Number of occupants must be at least 1") int numberOfOccupants,
        @Min(value = 1, message = "Max number of occupants must be at least 1") int maxNumberOfOccupants,
        boolean ownerOccupied,
        @NotEmpty(message = "Household name must not be blank") String name,
        List<Long> petIds
) {}



*/


package com.example.lab_7.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record NewHousehold(
        @NotBlank(message = "Eircode must not be blank")
        @Size(min = 5, max = 10, message = "Eircode must be between 5 and 10 characters")
        String eircode,

        @NotNull(message = "Number of occupants cannot be null")
        @Positive(message = "Number of occupants must be positive")
        Integer numberOfOccupants,

        @NotNull(message = "Max number of occupants cannot be null")
        @Positive(message = "Max number of occupants must be positive")
        Integer maxNumberOfOccupants,

        @NotNull(message = "Owner-occupied status must be provided")
        Boolean ownerOccupied
) {}
