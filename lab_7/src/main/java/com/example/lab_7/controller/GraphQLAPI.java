

package com.example.lab_7.controller;

import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.dtos.NewHousehold;
import com.example.lab_7.entities.Household;
import com.example.lab_7.service.HouseholdService;
import com.example.lab_7.service.exceptions.HouseholdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLAPI {

    private final HouseholdService householdService;

    // Query: Get all households
    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    // Query: Get a household by ID
    @QueryMapping
    public HouseholdDTO getHousehold(@Argument Long id) {
        return householdService.getHousehold(id)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with ID: " + id));
    }

    // Mutation: Create a household
    @MutationMapping
    public Household createHousehold(@Argument("input") NewHousehold newHousehold) {
        Household household = new Household();
        household.setEircode(newHousehold.eircode());
        household.setNumberOfOccupants(newHousehold.numberOfOccupants());
        household.setMaxNumberOfOccupants(newHousehold.maxNumberOfOccupants());
        household.setOwnerOccupied(newHousehold.ownerOccupied());
        return householdService.createHousehold(household);
    }

    // Mutation: Delete a household by eircode
    @MutationMapping
    public String deleteHouseholdByEircode(@Argument String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return "Household with eircode " + eircode + " deleted successfully.";
    }
}
