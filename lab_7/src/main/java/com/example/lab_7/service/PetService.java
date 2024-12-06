package com.example.lab_7.service;


import com.example.lab_7.dtos.PetDTO;
import com.example.lab_7.entities.Household;
import com.example.lab_7.entities.Pet;
import com.example.lab_7.entities.PetStatistics;
import com.example.lab_7.service.exceptions.NotFoundException;

import java.util.List;


public interface PetService {



    List<Pet> getAllPets();

    Pet getPetById(Long id);

    Pet updatePet(Long id, Pet pet);

    void deletePetById(Long id);

    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);



    void changePetName(Long id, String newName);

    PetStatistics getPetStatistics();

    Pet createPet(Pet pet);




}

