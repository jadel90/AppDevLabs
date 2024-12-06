
package com.example.lab_7.controller;

import com.example.lab_7.dtos.PetDTO;
import com.example.lab_7.entities.Pet;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.PetService;
import com.example.lab_7.service.exceptions.InvalidPetDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetREST {

    private final PetService petService;

    public PetREST(PetService petService) {
        this.petService = petService;
    }

    // Get all pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Get a pet by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetDTO getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        return convertToDTO(pet);
    }

    // Create a new pet
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PetDTO createPet(@PathVariable PetDTO petDTO) {
        // Convert DTO to entity and save
        Pet pet = petService.createPet(convertToEntity(petDTO));
        return convertToDTO(pet);
    }

    // Delete a pet by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
    }

    // Update a pet's details
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetDTO updatePet(@PathVariable Long id,  PetDTO petDTO) {
        Pet updatedPet = petService.updatePet(id, convertToEntity(petDTO));
        return convertToDTO(updatedPet);
    }

    // Change a pet's name
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/name")
    public void changePetName(@PathVariable Long id, String newName) {
        petService.changePetName(id, newName);
    }

    @GetMapping("/type/{animalType}")
    @ResponseStatus(HttpStatus.OK)
    public List<PetDTO> getPetsByAnimalType(@PathVariable String animalType) {
        // Fetch the list of pets by animal type
        List<Pet> pets = petService.findPetsByAnimalType(animalType);

        // Create an empty list to hold the PetDTOs
        List<PetDTO> petDTOs = new ArrayList<>();

        // Use a traditional for-loop to convert each Pet to a PetDTO
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i); // Get the pet at the current index
            PetDTO petDTO = convertToDTO(pet); // Convert the pet to a PetDTO
            petDTOs.add(petDTO); // Add the PetDTO to the list
        }

        // Return the list of PetDTOs
        return petDTOs;
    }

    // Get pets by breed
    @GetMapping("/breed/{breed}")
    @ResponseStatus(HttpStatus.OK)
    public List<PetDTO> getPetsByBreed(@PathVariable String breed) {
        List<Pet> pets = petService.findPetsByBreed(breed); // Fetch the list of pets by breed
        List<PetDTO> petDTOs = new ArrayList<>(); // Create an empty list to hold the converted DTOs

        // Iterate over each Pet entity
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i); // Get the pet at the current index
            PetDTO petDTO = convertToDTO(pet); // Convert the pet to a DTO
            petDTOs.add(petDTO); // Add the DTO to the list
        }

        return petDTOs; // Return the list of DTOs
    }



    // Get pet statistics
    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public PetStatistics getPetStatistics() {
        return petService.getPetStatistics();
    }


    // Convert PetDTO to Pet entity
    private Pet convertToEntity(PetDTO dto) {
        if (dto == null) {
            throw new InvalidPetDataException("PetDTO is null");
        }
        if (dto.name() == null || dto.name().trim().isEmpty()) {
            throw new InvalidPetDataException("Pet name is missing or empty");
        }
        if (dto.type() == null || dto.type().trim().isEmpty()) {
            throw new InvalidPetDataException("Pet type is missing or empty");
        }
        if (dto.age() <= 0) {
            throw new InvalidPetDataException("Pet age must be greater than zero");
        }

        Pet pet = new Pet();
        pet.setName(dto.name());
        pet.setAnimalType(dto.type());
        pet.setBreed(dto.breed());
        pet.setAge(dto.age());
        return pet;
    }

    // Convert Pet entity to PetDTO
    private PetDTO convertToDTO(Pet pet) {
        Long householdId = (pet.getHousehold() != null) ? pet.getHousehold().getId() : null;
        return new PetDTO(pet.getId(), pet.getName(), pet.getAnimalType(), pet.getBreed(), pet.getAge(), householdId);
    }

}
