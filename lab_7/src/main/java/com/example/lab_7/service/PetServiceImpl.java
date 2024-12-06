package com.example.lab_7.service;

import com.example.lab_7.daos.HouseholdRepository;
import com.example.lab_7.dtos.PetDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.Pet;
import com.example.lab_7.daos.PetRepository;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.exceptions.InvalidPetDataException;
import com.example.lab_7.service.exceptions.NotFoundException;
import com.example.lab_7.service.exceptions.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;



    @Override
    public Pet createPet(Pet pet) {
        if (pet == null) {
            throw new InvalidPetDataException("Pet object is null");
        }
        if (pet.getName() == null) {
            throw new InvalidPetDataException("Pet name is null");
        }
        if (pet.getAnimalType() == null) {
            throw new InvalidPetDataException("Animal type is null");
        }
        return petRepository.save(pet);
    }




    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }



    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + id));
    }

    @Override
    public void deletePetById(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("Pet not found with ID: " + id);
        }
        petRepository.deleteById(id);
    }




    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }








    @Override
    public void changePetName(Long id, String newName) {
        Pet pet = getPetById(id);
        pet.setName(newName);
        petRepository.save(pet);
    }






    @Override
    public Pet updatePet(Long id, Pet pet) {
        Pet existingPet = getPetById(id);
        if (pet.getName() != null) existingPet.setName(pet.getName());
        if (pet.getAnimalType() != null) existingPet.setAnimalType(pet.getAnimalType());
        if (pet.getBreed() != null) existingPet.setBreed(pet.getBreed());
        if (pet.getAge() > 0) existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }



    @Override
    public PetStatistics getPetStatistics() {
        List<Pet> allPets = petRepository.findAll(); // Fetch all pets

        if (allPets.isEmpty()) {
            return new PetStatistics(0, 0, 0, null, List.of());
        }

        int totalCount = allPets.size();
        int totalAge = 0;
        int oldestAge = 0;
        Pet oldestPet = null;

        // Calculate total age and find the oldest pet
        for (Pet pet : allPets) {
            totalAge += pet.getAge();

            if (pet.getAge() > oldestAge) {
                oldestAge = pet.getAge();
                oldestPet = pet;
            }
        }

        double averageAge = (double) totalAge / totalCount;

        return new PetStatistics(averageAge, oldestAge, totalCount, oldestPet, allPets);
    }






}

