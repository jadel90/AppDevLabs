package com.example.lab_7.controller;

import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.dtos.NewHousehold;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;
import com.example.lab_7.service.HouseholdService;
import com.example.lab_7.service.exceptions.BadDataException;
import com.example.lab_7.service.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/households")
public class HouseholdREST {

    private final HouseholdService householdService;

    // Get all households
    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public List<HouseholdDTO> getAllHouseholds() {
        List<Household> households = householdService.getAllHouseholds();
        List<HouseholdDTO> householdDTOs = new ArrayList<>();
        for (Household household : households) {
            householdDTOs.add(convertToDTO(household));
        }
        return householdDTOs;
    }

    // Get households with no pets
    @GetMapping("/no-pets")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseholdDTO> getHouseholdsWithNoPets() {
        List<Household> households = householdService.findHouseholdsWithNoPets();
        List<HouseholdDTO> householdDTOs = new ArrayList<>();
        for (Household household : households) {
            householdDTOs.add(convertToDTO(household));
        }
        return householdDTOs;
    }

    // Get household by ID
    @GetMapping("/id/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public HouseholdDTO getHouseholdById(@PathVariable Long id) throws NotFoundException {
        Household household = householdService.getHouseholdById(id);
        return convertToDTO(household);
    }

    // Create a new household
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HouseholdDTO addHousehold(@Valid @RequestBody NewHousehold newHousehold) throws BadDataException {
        Household household = new Household();
        household.setEircode(newHousehold.eircode());
        household.setNumberOfOccupants(newHousehold.numberOfOccupants());
        household.setMaxNumberOfOccupants(newHousehold.maxNumberOfOccupants());
        household.setOwnerOccupied(newHousehold.ownerOccupied());

        Household createdHousehold = householdService.createHousehold(household);
        return convertToDTO(createdHousehold);
    }

    // Get household by eircode
    @GetMapping("/eircode/{eircode}")
    //@ResponseStatus(HttpStatus.OK)
    public HouseholdDTO getHouseholdByEircode(@PathVariable String eircode) throws NotFoundException {
        Household household = householdService.getHouseholdByEircode(eircode);
        return convertToDTO(household);
    }

    // Update household by eircode
    @PatchMapping("/{eircode}")
    //@ResponseStatus(HttpStatus.OK)
    public HouseholdDTO updateHouseholdByEircode(
            @PathVariable String eircode,
            @Valid @RequestBody HouseholdDTO householdDTO) throws NotFoundException {
        Household updatedHousehold = householdService.updateHouseholdByEircode(eircode, householdDTO);
        return convertToDTO(updatedHousehold);
    }

    // Delete household by ID
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseholdById(@PathVariable Long id) throws NotFoundException {
        householdService.deleteById(id);
    }

    // Delete household by eircode
    @DeleteMapping("/eircode/{eircode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseholdByEircode(@PathVariable String eircode) throws NotFoundException {
        householdService.deleteByEircode(eircode);
    }

    // Get empty households
    @GetMapping("/empty")
    //@ResponseStatus(HttpStatus.OK)
    public List<HouseholdDTO> getEmptyHouseholds() {
        List<Household> households = householdService.findHouseholdsWithNoPets();
        List<HouseholdDTO> householdDTOs = new ArrayList<>();
        for (Household household : households) {
            householdDTOs.add(convertToDTO(household));
        }
        return householdDTOs;
    }

    // Get owner-occupied households
    @GetMapping("/owner-occupied")
    //@ResponseStatus(HttpStatus.OK)
    public List<HouseholdDTO> getOwnerOccupiedHouseholds() {
        List<Household> households = householdService.findOwnerOccupiedHouseholds();
        List<HouseholdDTO> householdDTOs = new ArrayList<>();
        for (Household household : households) {
            householdDTOs.add(convertToDTO(household));
        }
        return householdDTOs;
    }

    // Get household statistics
    @GetMapping("/statistics")
    //@ResponseStatus(HttpStatus.OK)
    public HouseholdStatistics getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }

    // Convert Household entity to DTO
    private HouseholdDTO convertToDTO(Household household) {
        return new HouseholdDTO(
                household.getEircode(),
                household.getNumberOfOccupants(),
                household.getMaxNumberOfOccupants(),
                household.isOwnerOccupied(),
                household.getId(),
                household.getPets()
        );
    }

    // Convert DTO to Household entity
    private Household convertToEntity(HouseholdDTO dto) {
        Household household = new Household();
        household.setEircode(dto.eircode());
        household.setNumberOfOccupants(dto.numberOfOccupants());
        household.setMaxNumberOfOccupants(dto.maxNumberOfOccupants());
        household.setOwnerOccupied(dto.ownerOccupied());
        return household;
    }


}
