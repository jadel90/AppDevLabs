package com.example.lab_7.daos;


import com.example.lab_7.entities.Pet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Deletes pets by name (case-insensitive)
    void deleteByNameIgnoreCase(String name);

    // Finds pets by animal type (case-insensitive)
    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    // Finds pets by breed and orders them by age
    List<Pet> findByBreedOrderByAge(String breed);

    // Finds pets by name and animal type
    List<Pet> findByNameAndAnimalType(String name, String animalType);

    // Custom delete method to remove pets by household eircode
    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.household.eircode = :householdEircode")
    void deleteByHouseholdEircode(@Param("householdEircode") String householdEircode);

    List<Pet> findByNameIgnoreCase(String name);

    List<Pet> findByAnimalType(String animalType);

    @Query("SELECT p FROM Pet p WHERE p.id = :id")
    Optional<Pet> findById(@Param("id") Long id);



}
