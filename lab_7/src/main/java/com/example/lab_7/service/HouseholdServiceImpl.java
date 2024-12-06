
/*
package com.example.lab_7.service;

import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;
import com.example.lab_7.daos.HouseholdRepository;
import com.example.lab_7.service.exceptions.HouseholdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household findByEircode(String eircode) {
        return householdRepository.findById(Long.valueOf(eircode))
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with eircode: " + eircode));
    }

    @Override
    public Household findByEircodeWithPets(String eircode) {
        Household household = householdRepository.findByEircodeWithPets(eircode);
        if (household == null) {
            throw new HouseholdNotFoundException("Household not found with eircode: " + eircode);
        }
        return household;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdByEircode(String eircode) {
        return findByEircode(eircode);
    }

    @Override
    public Household getHouseholdById(Long householdId) {
        return householdRepository.findById(Long.valueOf(String.valueOf(householdId)))
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with ID: " + householdId));
    }

    @Override
    public Household getHouseholdWithPets(String eircode) {
        return findByEircodeWithPets(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household household) {
        Household existingHousehold = findByEircode(eircode);
        existingHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
        existingHousehold.setOwnerOccupied(household.isOwnerOccupied());
        return householdRepository.save(existingHousehold);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        if (!householdRepository.existsById(Long.valueOf(eircode))) {
            throw new HouseholdNotFoundException("Household not found with eircode: " + eircode);
        }
        householdRepository.deleteById(Long.valueOf(eircode));
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public HouseholdStatistics getHouseholdStatistics() {
        List<Household> households = householdRepository.findAll();
        long emptyCount = households.stream().filter(Household::isEmpty).count();
        long fullCount = households.stream().filter(Household::isFull).count();
        return new HouseholdStatistics(emptyCount, fullCount);
    }


    @Override
    public void deleteHouseholdbyId(Long householdId) {
        if (householdRepository.existsById(Long.valueOf(String.valueOf(householdId)))) {
            householdRepository.deleteById(Long.valueOf(String.valueOf(householdId)));
        } else {
            throw new RuntimeException("Household not found");
        }
    }

    @Override
    public HouseholdDTO getHousehold(Long householdId) {
        Household household = householdRepository.findById(Long.valueOf(String.valueOf(householdId)))
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with ID: " + householdId));

        return new HouseholdDTO(
                household.getEircode(),
                household.getNumberOfOccupants(),
                household.getMaxNumberOfOccupants(),
                household.isOwnerOccupied(),
                household.getId(),
                household.getPets()
        );
    }


    @Override
    public List<HouseholdDTO> getHouseholdsWithNoPets() {
        return List.of();
    }
}
*/



/*
package com.example.lab_7.service;


import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;
import com.example.lab_7.daos.HouseholdRepository;
import com.example.lab_7.entities.Pet;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.exceptions.HouseholdNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    // Use eircode directly as String
    @Override
    public Household findByEircode(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);  // Use a proper method for finding by eircode
    }

    @Override
    public Household findByEircodeWithPets(String eircode) {
        Household household = householdRepository.findByEircodeWithPets(eircode);
        if (household == null) {
            throw new HouseholdNotFoundException("Household not found with eircode: " + eircode);
        }
        return household;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdByEircode(String eircode) {
        return findByEircode(eircode);
    }



    /*
    // `householdId` is correctly used as a Long
    @Override
    public Household getHouseholdById(Long householdId) {
        return householdRepository.findById(householdId)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with ID: " + householdId));
    }

     */


/*
import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;

import java.util.List;

@Override
    public Household getHouseholdWithPets(String eircode) {
        return findByEircodeWithPets(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household household) {
        Household existingHousehold = findByEircode(eircode);
        existingHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
        existingHousehold.setOwnerOccupied(household.isOwnerOccupied());
        return householdRepository.save(existingHousehold);
    }

    // Remove conversion to Long; use eircode as String
    @Override
    public void deleteHouseholdByEircode(String eircode) {
        Household household = findByEircodeWithPets(eircode);
        householdRepository.delete(household);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public HouseholdStatistics getHouseholdStatistics() {
        List<Household> households = householdRepository.findAll();
        long emptyCount = households.stream().filter(Household::isEmpty).count();
        long fullCount = households.stream().filter(Household::isFull).count();
        return new HouseholdStatistics(emptyCount, fullCount);
    }

    /*
    // Remove unnecessary conversions for `householdId` as Long
    @Override
    public void deleteHouseholdbyId(Long householdId) {
        if (!householdRepository.existsById(householdId)) {
            throw new HouseholdNotFoundException("Household not found with ID: " + householdId);
        }
        householdRepository.deleteById(householdId);
    }

     */






/*

    // Use Long householdId directly
    @Override
    public Optional<HouseholdDTO> getHousehold(Long householdId) {
        return householdRepository.findById(householdId)
                .map(household -> new HouseholdDTO(
                        household.getEircode(),
                        household.getNumberOfOccupants(),
                        household.getMaxNumberOfOccupants(),
                        household.isOwnerOccupied(),
                        household.getId(),
                        household.getPets()
                ));
    }

    @Override
    public void deleteHouseholdById(Long id) {

    }

    @Override
    public Household getHouseholdById(Long id) {
        // First, check if the household exists to avoid exceptions on deletion
        if (householdRepository.existsById(id)) {
            householdRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Household with ID " + id + " not found.");
        }
    }




    @Override
    public List<HouseholdDTO> getHouseholdsWithNoPets() {
        return List.of();
    }
}




*/


package com.example.lab_7.service;

import com.example.lab_7.dtos.HouseholdDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.HouseholdStatistics;
import com.example.lab_7.daos.HouseholdRepository;
import com.example.lab_7.service.exceptions.HouseholdNotFoundException;
import com.example.lab_7.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class HouseholdServiceImpl implements HouseholdService {

        @Autowired
        private HouseholdRepository householdRepository;

        @Override
        public Household findByEircode(String eircode) {
            return householdRepository.findByEircodeWithPets(eircode);  // Method to find by eircode with pets
        }

        @Override
        public Household findByEircodeWithPets(String eircode) {
            Household household = householdRepository.findByEircodeWithPets(eircode);
            if (household == null) {
                throw new HouseholdNotFoundException("Household not found with eircode: " + eircode);
            }
            return household;
        }

        @Override
        public Household createHousehold(Household household) {
            return householdRepository.save(household);
        }

        @Override
        public List<Household> getAllHouseholds() {
            return householdRepository.findAll();
        }



        @Override
        public Household getHouseholdWithPets(String eircode) {
            return findByEircodeWithPets(eircode);
        }

        @Override
        public Household updateHousehold(String eircode, Household household) {
            Household existingHousehold = findByEircode(eircode);
            existingHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
            existingHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
            existingHousehold.setOwnerOccupied(household.isOwnerOccupied());
            return householdRepository.save(existingHousehold);
        }

        @Override
        public void deleteHouseholdByEircode(String eircode) {
            if (householdRepository.existsByEircode(eircode)) {
                householdRepository.deleteByEircode(eircode);
            } else {
                throw new HouseholdNotFoundException("Household not found with eircode: " + eircode);
            }
        }

        @Override
        public List<Household> findHouseholdsWithNoPets() {
            return householdRepository.findHouseholdsWithNoPets();
        }

        @Override
        public List<Household> findOwnerOccupiedHouseholds() {
            return householdRepository.findByOwnerOccupiedTrue();
        }

    @Override
    public HouseholdStatistics getHouseholdStatistics() {
        List<Household> households = householdRepository.findAll();
        long emptyCount = 0;
        long fullCount = 0;

        for (Household household : households) {
            if (household.isEmpty()) {
                emptyCount++;
            }
            if (household.isFull()) {
                fullCount++;
            }
        }

        return new HouseholdStatistics(emptyCount, fullCount);
    }

        @Override
        public void deleteHouseholdById(Long id) {
            // Delete by ID if it exists, otherwise throw an exception
            if (householdRepository.existsById(id)) {
                householdRepository.deleteById(id);
            } else {
                throw new HouseholdNotFoundException("Household not found with ID: " + id);
            }
        }

    @Override
    public void updateNumberOfOccupants(Long id, int newOccupants) {

    }


    @Override
    public Household getHouseholdByEircode(String eircode) {
        return householdRepository.findByEircode(eircode)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with eircode: " + eircode));
    }


    public Household updateHouseholdByEircode(String eircode, HouseholdDTO householdDTO) throws NotFoundException {
        Household household = householdRepository.findByEircode(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));

        // Update fields if they are provided in the DTO
        if (householdDTO.numberOfOccupants() != null) {
            household.setNumberOfOccupants(householdDTO.numberOfOccupants());
        }
        if (householdDTO.maxNumberOfOccupants() != null) {
            household.setMaxNumberOfOccupants(householdDTO.maxNumberOfOccupants());
        }
        if (householdDTO.ownerOccupied() != null) {
            household.setOwnerOccupied(householdDTO.ownerOccupied());
        }

        return householdRepository.save(household);
    }

    @Override
    public void deleteByEircode(String eircode) throws NotFoundException {
        Household household = householdRepository.findByEircode(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));
        householdRepository.delete(household);
    }


    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!householdRepository.existsById(id)) {
            throw new NotFoundException("Household not found with ID: " + id);
        }
        householdRepository.deleteById(id);
    }



    @Override
        public Household getHouseholdById(Long id) {
            // Retrieve household by ID without deleting it
            return householdRepository.findById(id)
                    .orElseThrow(() -> new HouseholdNotFoundException("Household not found with ID: " + id));
        }

        @Override
        public Optional<HouseholdDTO> getHousehold(Long householdId) {
            return householdRepository.findById(householdId)
                    .map(household -> new HouseholdDTO(
                            household.getEircode(),
                            household.getNumberOfOccupants(),
                            household.getMaxNumberOfOccupants(),
                            household.isOwnerOccupied(),
                            household.getId(),
                            household.getPets()
                    ));
        }

    public List<HouseholdDTO> getHouseholdsWithNoPets() {
        // Converts households with no pets to DTO format using a traditional loop
        List<Household> households = householdRepository.findHouseholdsWithNoPets();
        List<HouseholdDTO> householdDTOs = new ArrayList<>();

        for (Household household : households) {
            HouseholdDTO dto = new HouseholdDTO(
                    household.getEircode(),
                    household.getNumberOfOccupants(),
                    household.getMaxNumberOfOccupants(),
                    household.isOwnerOccupied(),
                    household.getId(),
                    household.getPets()
            );
            householdDTOs.add(dto);
        }

        return householdDTOs;
    }

}
