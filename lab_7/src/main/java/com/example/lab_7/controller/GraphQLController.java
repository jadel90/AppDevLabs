
package com.example.lab_7.controller;

import com.example.lab_7.daos.HouseholdRepository;
import com.example.lab_7.daos.PetRepository;
import com.example.lab_7.dtos.NewPet;
import com.example.lab_7.entities.Pet;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLController {

    private final PetService petService;
    private final PetRepository petRepository;
    private final HouseholdRepository householdRepository;

    // Query: Get all pets
    @QueryMapping
    public List<Pet> getPets() {
        return petService.getAllPets();
    }

    // Query: Get pets by animal type
    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    // Query: Get pet statistics
    @QueryMapping
    public PetStatistics getStatistics() {
        return petService.getPetStatistics();
    }

    // Mutation: Create a new pet
    @MutationMapping
    public Pet createPet(@Argument("input") NewPet newPet) {
        if (newPet == null) {
            throw new IllegalArgumentException("NewPet input cannot be null");
        }

        // Create and save the pet
        Pet pet = new Pet();
        pet.setName(newPet.getName());
        pet.setAnimalType(newPet.getAnimalType());
        pet.setBreed(newPet.getBreed());
        pet.setAge(newPet.getAge());

        return petRepository.save(pet);
    }

    // Mutation: Update an existing pet
    @MutationMapping
    public Pet updatePet(@Argument Long id, @Argument String name, @Argument String animalType) {
        Pet existingPet = petService.getPetById(id);
        if (name != null) existingPet.setName(name);
        if (animalType != null) existingPet.setAnimalType(animalType);
        return petService.updatePet(id, existingPet);
    }

    // Mutation: Delete a pet by ID
    @MutationMapping
    public String deletePetById(@Argument Long id) {
        petService.deletePetById(id);
        return "Pet deleted successfully.";
    }
}
