package com.example.lab_7.service;

import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.exceptions.NotFoundException;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {

    Household findByEircode(String eircode);

    Household findByEircodeWithPets(String eircode);

    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Household getHouseholdByEircode(String eircode);
    Household getHouseholdById(Long householdId);
    Household getHouseholdWithPets(String eircode);

    Household updateHousehold(String eircode, Household household);
    void deleteHouseholdByEircode(String eircode);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    HouseholdStatistics getHouseholdStatistics();

    //HouseholdDTO getHousehold(Long householdId);

    List<HouseholdDTO> getHouseholdsWithNoPets();

    Optional<HouseholdDTO> getHousehold(Long householdId);

    void deleteHouseholdById(Long id);

    void updateNumberOfOccupants(Long id, @Positive(message = "Occupants must be positive") int newOccupants);

    Household updateHouseholdByEircode(String eircode, HouseholdDTO householdDTO) throws NotFoundException;


    void deleteByEircode(String eircode) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;
}